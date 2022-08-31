# android-fragment-navigation-webview
This is an template android project with navigating through fragment pages and web-view in a frgmented page

Configure API call to mongodb.cloud @ APIService.class. 
You can add API-KEY from the app UI
         mongodb_URL = "<URL>"; // Find url
         mongodb_API_KEY = "<API-KEY>";
         mongodb_DATASOURCE = "<DATASOURCE>";
         mongodb_DATABASE = "<DATABASE>>";
         mongodb_COLLECTION = "<COLLECTION>"; 

Follow points included

Navigate through fragment pages
Add Webview on fragment page 

Webview - How to load Webview in a frgmented page
Webview - Load HTML string to Webview as bytes,
Webview - Load page to Webview from a internaet/webserver

Webview - Load page to Webview from In-app  // This sample focused on this
Use JavaScript in WebView
Add assets folder at app/src/main/ (app/src/main/assets/)
Config location of html, js, css, images

Bind JavaScript code to Android code via app interface
  Create interfaces between your app code (client-side Android code) and your JavaScript code.
  your JavaScript code can call a method in your Android code 
CSS Spinner being shown when data is loaded
  <p> element innerText changers can not detected by onChange event. So PURE javascript implementation done to detect by when attribute change to mutations 
Use SharedPreferences to save app data such as token in android storage which is cross applications (in XML format)

Service call from web view  
  ajax call:  web -> web
  js call: web -> [web interface] -> app (java)
  js call: web -> [web interface] -> app (java - curl) -> [web interface]  -> web
