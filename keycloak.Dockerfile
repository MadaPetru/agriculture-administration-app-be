FROM quay.io/keycloak/keycloak:24.0.2

# Set environment variables for admin credentials
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Expose the default Keycloak port
EXPOSE 8080

# Set the command to run Keycloak in development mode
CMD ["start-dev"]
