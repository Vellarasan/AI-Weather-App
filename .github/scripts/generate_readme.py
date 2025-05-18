#!/usr/bin/env python3
import os
import subprocess
import glob
import re
import openai
from pathlib import Path

# Setup OpenAI client
openai.api_key = os.environ.get("OPENAI_API_KEY")
if not openai.api_key:
    raise ValueError("OPENAI_API_KEY environment variable must be set")

def get_repo_info():
    """Gather basic repository information."""
    try:
        repo_name = subprocess.check_output(
            ["git", "config", "--get", "remote.origin.url"],
            text=True
        ).strip()
        # Extract just the repo name from the URL
        repo_name = repo_name.split("/")[-1].replace(".git", "")
    except Exception:
        repo_name = os.path.basename(os.getcwd())
    
    return {
        "name": repo_name
    }

def get_file_structure():
    """Get the file structure of the repository."""
    # Ignore common directories and files
    ignore_patterns = [
        ".git", ".github", "__pycache__", "node_modules", 
        "*.pyc", "*.log", "*.lock", "*.env", "*.csv", "*.dat"
    ]
    
    file_paths = []
    for root, dirs, files in os.walk("."):
        # Apply directory exclusions
        dirs[:] = [d for d in dirs if not any(re.match(pattern, d) for pattern in ignore_patterns)]
        
        for file in files:
            if not any(re.match(pattern, file) for pattern in ignore_patterns):
                file_path = os.path.join(root, file)
                file_paths.append(file_path)
    
    return file_paths

def analyze_code_files(file_paths, max_files=10):
    """Analyze the most important code files."""
    # Prioritize key files
    priority_files = [
        "./package.json", "./requirements.txt", "./setup.py", "./Dockerfile",
        "./main.py", "./index.js", "./src/main", "./src/index"
    ]
    
    analyzed_files = []
    
    # First add priority files if they exist
    for pattern in priority_files:
        matching_files = [f for f in file_paths if f.startswith(pattern)]
        for file in matching_files:
            if file in file_paths:
                try:
                    with open(file, 'r', encoding='utf-8') as f:
                        content = f.read()
                    analyzed_files.append({
                        "path": file,
                        "content": content[:5000]  # Limit file content size
                    })
                    if len(analyzed_files) >= max_files:
                        return analyzed_files
                except Exception as e:
                    print(f"Error reading {file}: {e}")
    
    # Add other important files
    for file in file_paths:
        if file not in [f["path"] for f in analyzed_files]:
            # Skip files that are likely not code or config
            if file.endswith(('.png', '.jpg', '.jpeg', '.gif', '.svg', '.ico', '.woff', '.ttf')):
                continue
                
            try:
                with open(file, 'r', encoding='utf-8') as f:
                    content = f.read()
                    
                # Skip empty files
                if not content.strip():
                    continue
                    
                analyzed_files.append({
                    "path": file,
                    "content": content[:5000]  # Limit file content size
                })
                
                if len(analyzed_files) >= max_files:
                    break
            except Exception:
                # Skip files that can't be read as text
                continue
    
    return analyzed_files

def generate_readme(repo_info, file_structure, analyzed_files):
    """Generate README using OpenAI."""
    # Prepare data for OpenAI
    analyzed_data = {
        "repo_name": repo_info["name"],
        "file_count": len(file_structure),
        "analyzed_files": analyzed_files
    }
    
    # Format the files for the prompt
    files_text = "\n\n".join([
        f"FILE: {file['path']}\n```\n{file['content'][:1000]}...\n```" 
        for file in analyzed_files
    ])
    
    # Create the prompt
    prompt = f"""
    As an AI assistant, analyze the following GitHub repository and generate a comprehensive README.md file.
    
    Repository Name: {analyzed_data['repo_name']}
    Total Files: {analyzed_data['file_count']}
    
    Key Files:
    {files_text}
    
    Based on the code and configuration files above, create a README.md that includes:
    
    1. A concise title and description of what the project does
    2. Installation instructions
    3. Usage examples
    4. Key features
    5. File structure overview
    6. Prerequisites or dependencies
    7. How to contribute (if applicable)
    8. License information (if found)
    
    Format the README using proper Markdown syntax with headers, code blocks, and bullet points.
    """
    
    # Call OpenAI API
    response = openai.chat.completions.create(
        model="gpt-4o",  # Use appropriate model
        messages=[
            {"role": "system", "content": "You are an expert developer tasked with creating high-quality README files for GitHub repositories."},
            {"role": "user", "content": prompt}
        ],
        max_tokens=2000
    )
    
    return response.choices[0].message.content

def main():
    """Main function to generate README."""
    print("Gathering repository information...")
    repo_info = get_repo_info()
    
    print("Analyzing file structure...")
    file_structure = get_file_structure()
    
    print(f"Found {len(file_structure)} files, analyzing important ones...")
    analyzed_files = analyze_code_files(file_structure)
    
    print("Generating README using OpenAI...")
    readme_content = generate_readme(repo_info, file_structure, analyzed_files)
    
    print("Writing README.md...")
    with open("README.md", "w", encoding="utf-8") as f:
        f.write(readme_content)
    
    print("README.md generated successfully!")

if __name__ == "__main__":
    main()
