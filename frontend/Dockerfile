# Use an official node runtime as a parent image
FROM node:22-alpine

# Set the working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY /app/. . 
RUN npm install react-scripts
RUN npm install

# Build the React application
RUN npm run build

# Install serve to serve the build
RUN npm install -g serve

# Expose the port the app runs on
EXPOSE 3000

# Serve the build
CMD ["serve", "-s", "build"]
