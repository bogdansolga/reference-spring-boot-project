# Prompt Engineering Exercises for AI-Assisted Development

Practice using AI tools (Cursor, Windsurf, Claude Code, ChatGPT) to work with this Spring Boot codebase. Start with reading and analysis, then progress to writing and refactoring.

---

## Exercise 1: Basic Code Explanation

**Goal:** Understand what a piece of code does.

**Prompt:**
```
Explain what this file does: src/main/java/com/great/project/domain/model/Product.java
```

**What to observe:**
- Does the AI identify the JPA entity annotations?
- Does it explain the relationship with Section?
- Does it mention the cascade and fetch strategies?

---

## Exercise 2: Find Specific Functionality

**Goal:** Locate where something is implemented.

**Prompt:**
```
Where is user authentication handled in this project?
```

**What to observe:**
- Does it find `SecurityConfiguration.java`?
- Does it mention the in-memory user details?
- Does it explain HTTP Basic authentication?

---

## Exercise 3: Understand the Architecture

**Goal:** Get a high-level view of the project structure.

**Prompt:**
```
Describe the architecture of this Spring Boot project. What patterns does it follow? How are the layers organized?
```

**What to observe:**
- Does it identify Controller → Service → Repository pattern?
- Does it mention transaction management strategies?
- Does it note the caching and async configurations?

---

## Exercise 4: Code Review Request

**Goal:** Get feedback on existing code quality.

**Prompt:**
```
Review src/main/java/com/great/project/service/ProductService.java for potential improvements. Focus on transaction management and error handling.
```

**What to observe:**
- Are the suggestions actionable?
- Does it respect Spring conventions?
- Does it avoid over-engineering?

---

## Exercise 5: Add Documentation

**Goal:** Generate Javadoc comments for existing code.

**Prompt:**
```
Add Javadoc comments to the public methods in src/main/java/com/great/project/controller/ProductController.java
```

**What to observe:**
- Are parameter descriptions accurate?
- Are return types documented?
- Are HTTP status codes documented?

---

## Exercise 6: Write a Unit Test

**Goal:** Create tests for existing functionality.

**Prompt:**
```
Write unit tests for src/main/java/com/great/project/service/SectionService.java using JUnit 5 and Mockito. Mock the repository layer.
```

**What to observe:**
- Does it follow existing test patterns in the project?
- Does it use `@Mock` and `@InjectMocks` correctly?
- Does it cover success and error cases?

---

## Exercise 7: Simple Refactoring

**Goal:** Improve code without changing behavior.

**Prompt:**
```
Refactor the exception handling in ProductController.java to use more specific exception types while keeping the same HTTP responses.
```

**What to observe:**
- Is the refactoring minimal and focused?
- Does it integrate with the existing ExceptionHandlers?
- Does it preserve API behavior?

---

## Exercise 8: Add Input Validation

**Goal:** Enhance an endpoint with better validation.

**Prompt:**
```
Add Bean Validation annotations to ProductDTO to ensure name is not blank (max 100 chars) and price is positive. Update the controller to validate input.
```

**What to observe:**
- Does it use standard Jakarta Validation annotations?
- Does it add @Valid to the controller?
- Does it handle validation errors appropriately?

---

## Exercise 9: Implement a New Feature

**Goal:** Add new functionality following existing patterns.

**Prompt:**
```
Add a new GET endpoint at /v1/api/product/section/{sectionId} that returns all products belonging to a specific section. Follow the existing patterns for transaction management and response format.
```

**What to observe:**
- Does it add a repository method with proper query?
- Does it follow the service layer pattern?
- Does it handle the case when section doesn't exist?

---

## Exercise 10: Complex Feature with Multiple Files

**Goal:** Implement a feature that spans multiple layers.

**Prompt:**
```
Implement a product search feature:
1. Add a repository method to search products by name (partial match, case-insensitive)
2. Add a service method that calls the repository
3. Create a GET endpoint at /v1/api/product/search?q=term
4. Include proper validation and transaction management
Follow the existing patterns in the codebase.
```

**What to observe:**
- Does it create changes in the correct files?
- Does it use Spring Data JPA query methods or @Query?
- Does it handle edge cases (empty query, no results)?

---

## Tips for Effective Prompting

1. **Be specific** - Reference exact class names and packages when possible
2. **Set constraints** - "Follow existing patterns" prevents over-engineering
3. **One task at a time** - Complex prompts can lead to errors
4. **Review output** - Always verify AI suggestions before applying
5. **Iterate** - Refine your prompt if the result isn't right
