# File Upload and ZIP Conversion Application

This Spring Boot application allows you to upload a file, which will then be converted to a ZIP file with encryption. This README provides step-by-step instructions for setting up and using the application.

## Prerequisites

- Java Development Kit (JDK) installed on your system
- Maven installed on your system
- Basic understanding of Java and Spring Boot

## Setup and Running the Application

1. **Clone the Repository**: git clone https://github.com/CocoBhaiya/ZIP-Conversion-Application.git

2. **Configure Application Properties**:
    - Open the `application.properties` file located in `src/main/resources`.
    - Update the following properties as per your requirement:
        - `file.upload-dir`: Specify the directory where uploaded files will be stored.
        - `zip.password`: Set the password for encrypting the ZIP file.

3. **Build the Application**:
    - Open a terminal or command prompt.
    - Navigate to the root directory of the cloned repository.
    - Run the following command to build the application:
      ```
      mvn clean install
      ```

4. **Run the Application**:
    - After the build is successful, run the following command to start the Spring Boot application:
      ```
      java -jar target/<jar_filename>.jar
      ```
      Replace `<jar_filename>` with the actual name of the JAR file generated during the build.

5. **Access the Application**:
    - Once the application is up and running, you can access it through a web browser or an API client.

## Uploading a File and Converting to ZIP

To upload a file and convert it to a ZIP file, you can use a tool like cURL or Postman. Here's how you can do it using cURL:

```bash
curl --location 'http://localhost:8080/upload' \
--form 'file=@"<path_to_your_file>"'
