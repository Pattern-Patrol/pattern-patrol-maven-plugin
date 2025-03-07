#!/bin/bash
HOOKS_DIR="$(dirname "$0")/.git-hooks"
GIT_HOOKS_DIR="$(dirname "$0")/.git/hooks"

echo "Installing Git hooks from $HOOKS_DIR to $GIT_HOOKS_DIR..."

for hook in "$HOOKS_DIR"/*; do
    hook_name=$(basename "$hook")
    cp "$hook" "$GIT_HOOKS_DIR/$hook_name"
    chmod +x "$GIT_HOOKS_DIR/$hook_name"
    echo "✔ Installed $hook_name"
done

echo "✅ Git hooks installed successfully!"
