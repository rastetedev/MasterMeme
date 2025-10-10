
![Banner](/assets/cover.png)

# 🖼️ Master Meme: Create Your Masterpiece

Master Meme is the definitive application for creating, managing, and sharing high-impact memes on the Android platform. It offers a complete set of tools to customize templates, manipulate text with precision, and organize your favorite creations in a customizable gallery.

## 🌟 Overview & Variants
This project is designed with two levels of complexity to offer development flexibility:

MVP (Minimum Viable Product): Includes essential features for gallery management, meme creation with basic font size control, and save/share options.

Extended Version: Adds advanced features such as template search and detailed text editing controls (font, size, and color).

This README describes the complete feature set of the Extended Version.

## 🚀 Key Features
### Gallery and Meme Management (Home)
- Grid View: Displays all user-created memes in a two-column grid.

- Favorites System: Tap the heart icon to mark/unmark memes as favorites.

- Smart Sorting: Allows sorting the grid by "Favourites first" (default value, with the latest favorited creation first) or by "Newest first" (creation date).

- Selection Mode: Long-press a meme to enter multi-selection mode, allowing batch sharing or deleting multiple memes at once.

### Advanced Meme Editing (Create Meme Screen)
- Template Loading: Quickly select a template from a sliding Bottom Sheet.
  (Extended) Template Search: The selection sheet includes a quick search function to filter templates by name.

- Draggable Text Boxes: Add multiple text boxes that can be dragged and resized, restricted to the boundaries of the image template.

- History Control: Implementation of Undo and Redo functions, maintaining a history of up to 5 actions.

- Text Customization (Extended):

  Font, Color, and Size Changes via Modal Controls.

- Save and Share Flow: Dual Saving: A "Save meme" button opens a Bottom Sheet with two options: Save to the device gallery or launch the system share dialog.

## 🛠️ Key Techonologies

| Technology         | Purpose                                                                                        | 
|--------------------|------------------------------------------------------------------------------------------------|
| Kotlin             | Primary and modern development language for Android.                                           |
| Android Jetpack    | Utilization of modern components, such as Lifecycle and ViewModel, for architectural purposes. |
| Jetpack Compose    | Designing the user interface with a Material Design approach.                                  |
| Room (Persistence) | Local storage of created memes and their state (favorite, date).                               |


## ✨ ✨ Differentiation Potential (Value-Added Suggestions)
These features go beyond the base requirements and would make "Master Meme" a truly unique application:

- Text Detection for Auto-Meme (ML Kit): Utilize the ML Kit Text Recognition API to scan user-uploaded images and automatically suggest pre-positioned text boxes.

- Quick Filters and Borders: Implement a set of image processing filters (Black and White, Sepia) and the ability to add a classic thick black meme border.

- Animated Export (GIF/Video): Offer an option to export the creation as an animated GIF or a short video.

## ⚙️ Environment Setup
To get this project running:

    1. Clone the Repository: git clone https://github.com/rastetedev/MasterMeme.git

    2. Open in Android Studio.

    3. Sync Gradle.

    4. Run: Execute the application on an emulator or physical device with Android 8.0 (API 26) or higher.

## 🤝 🤝 Contributions
Contributions are welcome. If you have suggestions or wish to propose an improvement or bug fix, please open an issue or submit a Pull Request.