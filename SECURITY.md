# Security Policy

## Credential handling

- Never commit real database passwords, API keys, access tokens, or private keys.
- Keep secrets in environment variables or private IDE run configurations.
- `.env.example` must contain placeholders only. Spring Boot does not load `.env` automatically without an additional library/configuration.
- Review staged changes before committing.
- If a secret has been pushed to a public repository, revoke or rotate it immediately. Removing it from the latest file does not remove it from Git history.

## Reporting

Do not publish sensitive details in public issues. Contact the repository owner privately through their GitHub profile.
