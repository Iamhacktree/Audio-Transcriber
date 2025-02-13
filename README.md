# Audio Transcriber

## Overview
This project is an **Audio to Text Transcriber** that allows users to upload an audio file and receive a transcribed text response. It is built using **React** for the frontend and **Spring Boot** with **OpenAI's Whisper API** for the backend.

## Features
- Upload audio files
- Transcribe audio to text using OpenAI's Whisper API
- Display the transcribed text in real-time

## Tech Stack
### Frontend (React)
- React.js (Vite)
- Axios (for API requests)
- CSS (for styling)

### Backend (Spring Boot)
- Spring Boot (Java)
- OpenAI API (Whisper for transcription)
- Multipart File Handling
- REST API

## Setup Instructions

### Prerequisites
- Node.js & npm (for React frontend)
- Java 17+ (for Spring Boot backend)
- OpenAI API Key (for transcription services)

### Setting Up the OpenAI API Key
1. Sign up on [OpenAI](https://openai.com/) and obtain your API key.
2. Open the backend project and navigate to `application.properties`.
3. Add the following line, replacing `your_openai_api_key` with your actual key:
   ```properties
   spring.ai.openai.api-key=your_openai_api_key
   ```
4. Ensure the key is kept secure and not shared publicly.

### Backend Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/audio-transcriber.git
   cd audio-transcriber
   ```
2. Configure the OpenAI API key as shown above.
3. Run the Spring Boot backend:
   ```sh
   mvn spring-boot:run
   ```

### Frontend Setup
1. Navigate to the frontend directory:
   ```sh
   cd frontend
   ```
2. Install dependencies:
   ```sh
   npm install
   ```
3. Start the React application:
   ```sh
   npm run dev
   ```

## Usage
1. Open the frontend in your browser (`http://localhost:5173` if using Vite).
2. Click the "Choose File" button and select an audio file.
3. Click "Upload and Transcribe" to send the file to the backend.
4. View the transcription results displayed on the screen.

## API Endpoints
### `POST /api/transcriber`
- **Description:** Accepts an audio file and returns the transcribed text.
- **Request:** Multipart file upload
- **Response:** JSON containing transcribed text

## Screenshots
### Upload Page
'''![Screenshot 2025-02-13 191154](https://github.com/user-attachments/assets/29305d5d-d548-4e66-b477-75ae3a6dd45f)

## Troubleshooting
- Ensure the backend server is running (`http://localhost:8080` by default).
- Verify the OpenAI API key is correctly set.
- Check console logs for errors in the frontend (`npm run dev`).

## Future Improvements
- Support for multiple languages
- Improved UI/UX
- Streaming transcription

---
**Happy Coding! 🚀**

