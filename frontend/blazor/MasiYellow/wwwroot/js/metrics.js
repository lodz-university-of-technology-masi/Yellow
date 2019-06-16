// import html2canvas from 'html2canvas'
// [INFO] This function does not work properly with scrolling.
// The position of the mouse is always taken relative to the upper left corner. 
function calculateMouseEvents(event) {
  calculateLength(event.clientX, event.clientY);
  clickCounter++;
}

let url = 'http://212.191.92.88:6061/api/v1/metrics'

let clickCounter = 0;
let length = 0;
let lastX = 0;
let lastY = 0;
let startTime = 0;
let endTime = 0;
let time = 0;

function calculateLength(x, y) {
  length += Math.sqrt((x - lastX) * (x - lastX) + (y - lastY) * (y - lastY));
  lastX = x;
  lastY = y;
  return length;
}

let status = false;

// This function runs after press any key.
// After press any key printMousePos function is run and time between keypresses
// is measured.
function checkKeysCombinantion(e) {
  if(e.code == 'KeyD' && e.shiftKey) {
    if (status === false) {
      console.log('Start collecting data');
      startTime = Date.now();
      document.addEventListener('click', calculateMouseEvents);
      status = true;
    } else {
      console.log('End collecting data');
      stopDataCollecting();
      resetValues();
    }
  }
  if(e.code == 'KeyW' && e.shiftKey) {
    if(status == true) {
      console.log('Cancel collecting data');
      document.removeEventListener('click', calculateMouseEvents);
      status = false;
      resetValues();
    }
  }
  if(e.code == 'KeyR' && e.shiftKey) {
    if(status == true) {
      console.log('Strop collecting data');
      stopDataCollecting();
      resetValues();
    }
  }
}
 
function stopDataCollecting() {
  document.removeEventListener('click', calculateMouseEvents);
  endTime = Date.now();
  time = endTime - startTime;
  status = false;
  postAction();
}

function resetValues() {
  length = 0;
  lastX = 0;
  lastY = 0;
  startTime = 0;
  endTime = 0;
  time = 0;
  clickCounter = 0;
}

function postAction() {
  let obj = new Object();

  obj.RES_W = window.innerWidth;
  obj.RES_H = window.innerHeight;
  obj.MC = clickCounter;
  obj.TIME = time;
  obj.DIST = length;

  let jsonString= JSON.stringify(obj);

  let xhttp = new XMLHttpRequest();
  xhttp.open('POST', url, true);
  xhttp.setRequestHeader("Content-Type", "application/json");
  xhttp.setRequestHeader('testuser', 'password');
  xhttp.send(jsonString);
  console.log(JSON.parse(jsonString));
}

document.addEventListener('keypress',  checkKeysCombinantion);