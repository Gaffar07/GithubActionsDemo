FROM maven:3.9.6-eclipse-temurin-17

RUN apt-get update && apt-get install -y wget gnupg unzip curl \
    libnss3 libgconf-2-4 libxi6 libxcomposite1 libxcursor1 \
    libxdamage1 libxrandr2 libasound2 fonts-liberation

RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor > /usr/share/keyrings/google-linux.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-linux.gpg] http://dl.google.com/linux/chrome/deb/ stable main" \
       > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable
    

WORKDIR /app
COPY . .
CMD ["mvn", "test"]
