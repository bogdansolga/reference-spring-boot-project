# Hands-On Exercises: AI Integration with Spring Boot

These exercises progressively introduce AI/LLM integration into the reference project. Each exercise includes a sample prompt to help you get started.

---

## Exercise 1: Your First LLM Interaction

**Objective:** Make your first API call to an LLM and display the response.

**Task:** Create a simple class that sends a prompt to the LLM API and logs the response.

**File to create:** `src/main/java/com/great/project/ai/HelloAI.java`

```java
// Use the OpenAI or Anthropic SDK to send a simple message
// Log the response using SLF4J
```

**Sample Prompt:**
```
You are a helpful assistant. Say hello and introduce yourself in one sentence.
```

**Success Criteria:**
- Class compiles and runs without errors
- Response is logged to the console

---

## Exercise 2: Environment Setup and API Key Management

**Objective:** Properly configure API keys using Spring's configuration system.

**Task:**
1. Add your API key to `application.yml` (or use environment variables)
2. Create a configuration class that injects the API key via `@Value`
3. Throw a meaningful exception if the key is missing

**File to create:** `src/main/java/com/great/project/config/AIConfig.java`

**Sample Prompt:**
```
Confirm this connection works by responding with only: "Connection successful"
```

**Success Criteria:**
- API key is not hardcoded
- Clear error message when key is missing
- Connection test passes

---

## Exercise 3: Create an AI-Powered REST Endpoint

**Objective:** Build a Spring controller that forwards requests to an LLM.

**Task:** Create an endpoint at `/v1/api/ai/chat` that:
1. Accepts a POST request with a `message` field
2. Sends the message to the LLM
3. Returns the AI response as JSON

**File to create:** `src/main/java/com/great/project/controller/AIController.java`

**Sample Prompt (for testing your endpoint):**
```
What are three benefits of using Spring Boot?
```

**Success Criteria:**
- Endpoint responds to POST requests
- Returns proper JSON response
- Handles errors gracefully with @ControllerAdvice

---

## Exercise 4: Product Description Generator

**Objective:** Use AI to generate marketing descriptions for products.

**Task:** Create an endpoint `/v1/api/ai/product-description` that:
1. Accepts a product name and price
2. Generates a compelling product description
3. Returns the generated text

**File to create:** `src/main/java/com/great/project/service/AIProductService.java`

**Sample Prompt (to send to the LLM):**
```
Generate a short, engaging product description (2-3 sentences) for the following product:

Name: {{productName}}
Price: ${{price}}

The description should highlight value and appeal to customers.
```

**Success Criteria:**
- Accepts product details via POST
- Returns creative, contextual descriptions
- Different products yield different descriptions

---

## Exercise 5: System Prompts and Role Definition

**Objective:** Learn to use system prompts to control AI behavior.

**Task:** Create a product assistant endpoint `/v1/api/ai/assistant` that:
1. Uses a system prompt to define the assistant's role
2. Only answers questions about products in our catalog
3. Politely declines off-topic questions

**File to create:** `src/main/java/com/great/project/service/AIAssistantService.java`

**System Prompt:**
```
You are a helpful shopping assistant for an online store. You help customers with questions about products, pricing, and recommendations.

Rules:
- Only discuss products, shopping, and related topics
- If asked about unrelated topics, politely redirect to shopping
- Be friendly and concise
- If you don't know something, say so honestly
```

**Success Criteria:**
- Assistant stays in character
- Off-topic questions are redirected
- Responses are helpful and relevant

---

## Exercise 6: Integrating Database Data with AI

**Objective:** Combine real product data from the database with AI responses.

**Task:** Create an endpoint `/v1/api/ai/product-info/{id}` that:
1. Fetches the product from the database using ProductService
2. Sends product details to the LLM
3. Returns an AI-enhanced product summary

**File to create:** `src/main/java/com/great/project/controller/AIProductController.java`

**Sample Prompt (to send to the LLM):**
```
Based on this product information, provide a helpful summary for a customer:

Product: {{product.name}}
Price: ${{product.price}}
Category: {{section.name}}

Include: what the product is, who it's for, and why it's a good choice.
```

**Success Criteria:**
- Fetches real product data from H2 database
- AI response incorporates actual product details
- Returns 404 for non-existent products

---

## Exercise 7: Smart Product Recommendations

**Objective:** Build an AI-powered recommendation system.

**Task:** Create an endpoint `/v1/api/ai/recommend` that:
1. Accepts a user's preferences or current product ID
2. Fetches available products from the database
3. Uses AI to recommend the best matches

**File to create:** `src/main/java/com/great/project/service/AIRecommendationService.java`

**Sample Prompt (to send to the LLM):**
```
You are a product recommendation engine. Given the user's interest and our available products, suggest the best matches.

User's interest: {{userInterest}}

Available products:
{{#products}}
- {{name}} (${{price}}) - Category: {{sectionName}}
{{/products}}

Recommend 1-3 products with a brief explanation for each.
```

**Success Criteria:**
- Recommendations are based on actual database products
- AI explains why each product is recommended
- Handles empty or invalid preferences gracefully

---

## Exercise 8: Natural Language Product Search

**Objective:** Allow users to search products using natural language.

**Task:** Create an endpoint `/v1/api/ai/search` that:
1. Accepts a natural language query
2. Uses AI to interpret the intent
3. Returns matching products from the database

**File to create:** `src/main/java/com/great/project/service/AISearchService.java`

**Sample Queries to Support:**
```
"I need something affordable"
"What's the most expensive item?"
"Show me everything under $50"
```

**Sample Prompt (to send to the LLM):**
```
Analyze this search query and extract the search criteria as JSON.

Query: "{{userQuery}}"

Available sections in database: Goodies

Return JSON with this structure:
{
  "section": "section name or null",
  "maxPrice": number or null,
  "minPrice": number or null,
  "keywords": ["relevant", "keywords"]
}
```

**Success Criteria:**
- Natural language queries are interpreted correctly
- Returns real products matching the intent
- Handles ambiguous queries gracefully

---

## Exercise 9: Structured Output with Validation

**Objective:** Ensure AI responses conform to expected schemas using Java records.

**Task:** Create an endpoint `/v1/api/ai/analyze-review` that:
1. Accepts a product review text
2. Uses AI to analyze sentiment and extract key points
3. Validates the AI response against a Java record/DTO

**File to create:** `src/main/java/com/great/project/dto/ReviewAnalysisDTO.java`

**DTO to use:**
```java
public record ReviewAnalysisDTO(
    Sentiment sentiment,  // POSITIVE, NEUTRAL, NEGATIVE
    int score,            // 1-5
    List<String> keyPoints,  // max 3
    boolean recommendation
) {
    public enum Sentiment { POSITIVE, NEUTRAL, NEGATIVE }
}
```

**Sample Prompt (to send to the LLM):**
```
Analyze this product review and return a JSON object with:
- sentiment: "POSITIVE", "NEUTRAL", or "NEGATIVE"
- score: 1-5 rating based on the review
- keyPoints: up to 3 main points from the review
- recommendation: true if the reviewer would recommend, false otherwise

Review: "{{reviewText}}"

Return only valid JSON, no additional text.
```

**Success Criteria:**
- AI responses are parsed into the DTO
- Invalid responses are handled with retry or error
- Type-safe response through the controller

---

## Exercise 10: Multi-Step Shopping Assistant Agent

**Objective:** Build an AI agent that can perform multiple steps to help users.

**Task:** Create an endpoint `/v1/api/ai/shopping-agent` that:
1. Understands complex user requests
2. Decides which actions to take (search, recommend, describe)
3. Executes multiple steps and compiles the response

**File to create:** `src/main/java/com/great/project/service/AIShoppingAgentService.java`

**Sample User Requests:**
```
"Find me the cheapest product and tell me why I should buy it"
"What products do you have and which is best value?"
"Compare the first and last products by price"
```

**System Prompt:**
```
You are a shopping assistant agent. You can perform these actions:
1. SEARCH: Find products by criteria
2. DESCRIBE: Get detailed description of a product
3. RECOMMEND: Suggest products based on preferences
4. COMPARE: Compare two or more products

For each user request:
1. Determine which actions are needed
2. Specify the parameters for each action
3. Return your plan as JSON:

{
  "actions": [
    {"type": "SEARCH", "params": {"query": "..."}},
    {"type": "DESCRIBE", "params": {"productId": 1}}
  ]
}
```

**Success Criteria:**
- Agent correctly interprets complex requests
- Multiple actions are executed in sequence
- Final response combines all gathered information
- Handles failures in individual steps gracefully

---

## Tips for All Exercises

1. **Error Handling:** Use @ControllerAdvice for centralized exception handling
2. **Timeouts:** Configure RestTemplate/WebClient timeouts for API calls
3. **Rate Limiting:** Be mindful of API rate limits during development
4. **Logging:** Use SLF4J for debugging AI interactions
5. **Testing:** Write unit tests with mocked LLM responses

## Useful Commands

```bash
# Run the application
mvn spring-boot:run

# Run tests
mvn test

# Run specific test
mvn test -Dtest=ProductControllerTest

# Package
mvn clean package
```

## Sample API Client Setup (OpenAI with RestTemplate)

```java
@Service
public class OpenAIService {

    @Value("${openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String chat(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
            "model", "gpt-4o-mini",
            "messages", List.of(
                Map.of("role", "user", "content", message)
            )
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            "https://api.openai.com/v1/chat/completions",
            request,
            Map.class
        );

        // Extract and return the response content
        // ...
    }
}
```

## Sample API Client Setup (Anthropic with RestTemplate)

```java
@Service
public class AnthropicService {

    @Value("${anthropic.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String chat(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        headers.set("anthropic-version", "2023-06-01");
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
            "model", "claude-sonnet-4-20250514",
            "max_tokens", 1024,
            "messages", List.of(
                Map.of("role", "user", "content", message)
            )
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            "https://api.anthropic.com/v1/messages",
            request,
            Map.class
        );

        // Extract and return the response content
        // ...
    }
}
```
