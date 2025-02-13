package com.Project.audio_transcriber.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.api.OpenAiAudioApi.TranscriptResponseFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transcriber")
public class TranscriptionController {
	private final OpenAiAudioTranscriptionModel transcriptModel;
	
	//we get the model here
	public TranscriptionController(@Value("${spring.ai.openai.api-key}") String apiKey) {
		if (apiKey == null) {
            throw new IllegalStateException("OpenAI API key is missing in environment variables");
        }
        OpenAiAudioApi openAiAudioApi = new OpenAiAudioApi(apiKey);
        this.transcriptModel = new OpenAiAudioTranscriptionModel(openAiAudioApi);
    }
	
	@PostMapping
	public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file) throws IOException{
		File tempFile = File.createTempFile("audio", ".wav");
		file.transferTo(tempFile);
		
		//this will set the audio transcription options for the audio that we want to perform on
		OpenAiAudioTranscriptionOptions transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
			    .responseFormat(TranscriptResponseFormat.TEXT)
			    .withLanguage("en")
			    .temperature(0f)
			    .build();
		
		FileSystemResource audioFile = new FileSystemResource(tempFile);
		
		//AudioTranscriptionPrompt is combining both audio file and transcriptionOptions as request to be send it to AI model
		AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audioFile, transcriptionOptions);
		AudioTranscriptionResponse response = transcriptModel.call(transcriptionRequest);
		
		tempFile.delete();
		return new ResponseEntity<>(response.getResult().getOutput(), HttpStatus.OK);
		
	}
}
