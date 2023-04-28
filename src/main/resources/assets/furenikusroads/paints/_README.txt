OwO, whats this? A new type of resource for asset packs to use??

This is where internal paint models are loaded from now. If you want to change them, change them here instead of in /models/ or /blockstates/. You actually cant change the blockstate itself now, it literally doesn't exist sorry. Blame the system for sucking.

BEFORE YOU CHANGE ANY: Why are you changing them? In almost all cases it's better to use the custom paints system to just add new variants (for example, different languages). If you have a real reason then feel free to continue, but just consider others who would use your resource pack; do they need these changes, or would they rather have new blocks instead?

To use these models, create a new JSON on my editor: http://fureniku.com/roads/editor/, or just edit it visually if you prefer. Grids can be 16x16, 32x32 or 64x64. a "1" represents paint, and "0" represents a gap. 

For the performance-concious; two neighbouring paints will not render the side between them, just like a full-size block wouldn't.

Ask in Discord if you get stuck! 