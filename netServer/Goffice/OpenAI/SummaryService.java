import com.openai.client.model.CompletionCreateResponse;

public class SummaryService {
    private OpenAIClient client;

    public SummaryService() {
        this.client = new OpenAIClient(); 
    }

    public String summarizeText(String text) {
        CompletionCreateResponse response = client.completions().createPrompt(text, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        return response.getChoices().get(0).getText();
    }
}
