# Audio Transcription and Summary Application Documentation

## Overview

This application serves as a tool for transcribing audio files and generating summaries of the transcribed text. It operates as a server that accepts audio file uploads and uses OpenAI's advanced AI models for processing. The application is designed to be lightweight, user-friendly, and efficient, catering to users who need quick transcriptions and summaries of audio recordings.

## Getting Started

To begin using the application, ensure that you have the necessary environment setup, including Java and Maven for dependency management. The application is built to run on a server listening on port 8001.

### Prerequisites

- Java JDK (version 8 or later)
- Maven for dependency management
- Internet connection for accessing OpenAI services

### Installation

1. Clone the repository or download the source code.
2. Navigate to the application directory.
3. Run `mvn clean install` to build the application and install the dependencies.

## Usage

Once the application is running, it listens for audio file uploads on port 8001. The files can be uploaded via a simple HTTP POST request to the `/upload` endpoint.

### Uploading an Audio File

To upload an audio file for transcription and summarization:

1. Make a POST request to `http://[server-address]:8001/upload`.
2. Include the audio file in the request with the key 'file'.

The server will handle the file, transcribe the audio using OpenAI's API, and subsequently generate a summary of the transcribed text.

## Privacy and Security

In line with our commitment to privacy and security, sensitive information such as API keys and specific server configurations have been excluded from this documentation and the application code. This measure is to prevent unauthorized access and to maintain the confidentiality of user data.

## Support and Contact

Should you need further assistance, have questions, or require access to sensitive information for academic purposes, please do not hesitate to contact us. Our team is dedicated to providing support and ensuring that your experience with the application is seamless and productive.
