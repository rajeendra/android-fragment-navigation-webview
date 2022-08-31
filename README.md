# android-fragment-navigation-webview
This is an template android project with navigating through fragment pages and web-view in a frgmented page

Configure API call to mongodb.cloud @ APIService.class.<br />
You can add API-KEY from the app UI<br />
         &emsp;mongodb_URL = "<URL>"; // Find url<br />
         &emsp;mongodb_API_KEY = "<API-KEY>";<br />
         &emsp;mongodb_DATASOURCE = "<DATASOURCE>";<br />
         &emsp;mongodb_DATABASE = "<DATABASE>>";<br />
         &emsp;mongodb_COLLECTION = "<COLLECTION>";<br />
         
<P>Follow points included</p>
<P>
Navigate through fragment pages<br />
Add Webview on fragment page
</p>

<p>
Webview - How to load Webview in a frgmented page<br />
Webview - Load HTML string to Webview as bytes,<br />
Webview - Load page to Webview from a internaet/webserver<br />
</p>

<p>
Webview - Load page to Webview from In-app  // This sample focused on this<br />
Use JavaScript in WebView<br />
Add assets folder at app/src/main/ (app/src/main/assets/)<br />
Config location of html, js, css, images
</p>
<p>
Bind JavaScript code to Android code via app interface<br />
  &emsp;Create interfaces between your app code (client-side Android code) and your JavaScript code.<br />
  &emsp;your JavaScript code can call a method in your Android code <br />
CSS Spinner being shown when data is loaded<br />
element innerText changers can not detected by onChange event. So PURE javascript implementation done to detect by when attribute change to mutations<br />
Use SharedPreferences to save app data such as token in android storage which is cross applications (in XML format)<br />
</p>
<p>
Service call from web view<br />  
  &emsp; ajax call:  web -> web<br />
  &emsp; js call: web -> [web interface] -> app (java)<br />
  &emsp; js call: web -> [web interface] -> app (java - curl) -> [web interface]  -> web
</p>  
