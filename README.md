# RTSP Streamer

RTSP Streamer is a simple Android application that allows users to stream video over RTSP (Real-Time Streaming Protocol) built using **LibVLC**. 

## Features
- **Play and Pause RTSP Streams**: Users can enter an RTSP URL and stream the video.
- **Force TCP Mode**: Enables streaming over TCP instead of UDP.
- **Optimize for Live Streaming**: Adjusts playback settings to minimize latency for live feeds.

## Requirements
- Android Studio (latest version recommended)
- Minimum Android API Level 21 (Lollipop)
- Internet permission enabled in `AndroidManifest.xml`

## How to Build and Run
1. **Clone the Repository**
   ```sh
   git clone https://github.com/yourusername/RTSP-Streamer.git
   cd RTSP-Streamer
   ```
2. **Open in Android Studio**
    - Launch Android Studio and open the cloned project.
    - Let Gradle sync dependencies.

3. **Build the Project**
    - Ensure you have a connected device or an emulator.
    - Click **Run ▶** or use:
      ```sh
      ./gradlew assembleDebug
      ```

## License
This project is licensed under the MIT License.

## Acknowledgments
- **LibVLC**: Open-source multimedia framework used for RTSP playback.
- **VLC Android**: Reference implementation for video playback on Android.
- 
---

For any issues, feel free to open an issue on the GitHub repository!