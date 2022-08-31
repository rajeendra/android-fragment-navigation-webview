var signin = false;


// PURE JS
window.onload = function(){

    //document.getElementById("ajaxResponse").addEventListener("change", function() {
    //    hideSpinner();
    //});

    // Below code snippet need by spinner to hide, when the result being loaded to <p> element
    var element = document.querySelector('#ajaxResponse');

    var observer = new MutationObserver(function(mutations) {
      mutations.forEach(function(mutation) {
        if (mutation.type === "attributes") {
          //console.log("attributes changed")
          hideSpinner();
        }
      });
    });

    observer.observe(element, {
      attributes: true //configure it to listen to attribute changes
    });
}

function showAndroidToast(toast) {
    Android.showToast(toast);
}

function ajaxCall() {
  const xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
    document.getElementById("ajaxResponse").innerHTML = this.responseText;
  }
  xhttp.open("GET", "https://reqres.in/api/users?page=2");
  xhttp.send();
}

function clearResponse() {
    document.getElementById("ajaxResponse").innerHTML = '';
}

function mongoCloudCall(data){
    var pTag = document.getElementById('ajaxResponse');
    var resultFromApp;

    //pTag.setAttribute('data-text', 'NO'  );
    showSpinner();

    // This delay is to, spinner to load before the thread is being hold by the
    // service call to Android
    setTimeout(function (){
        resultFromApp = Android.mongoCloudCall(data);

        // This is need to trigger when the data is fetched for spinner to hide
        // Listen by above : observer.observe
        pTag.setAttribute('data-text', 'OK'  );

        if(resultFromApp==null)
            resultFromApp = "Error in API call, Make sure you saved your API key";

        document.getElementById("ajaxResponse").innerHTML = resultFromApp;
    }, 300);
}

function showSpinner(){
    document.getElementById("loader").style.display = "block";
    return true;
}

function hideSpinner(){
    document.getElementById("loader").style.display = "none";
}