let clickCounter = 0;

// [INFO] This function does not work properly with scrolling.
// The position of the mouse is always taken relative to the upper left corner.
// TODO: handle scrolling 
function printMousePos(event) {
  calculateLength(event.clientX, event.clientY);
  clickCounter++;
  document.body.textContent = 'X: ' + event.clientX + ', Y: ' + event.clientY +
      ' length: ' + length + ' number of clicks: ' + clickCounter;
}

let length = 0;
let lastX = 0;
let lastY = 0;

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
// TODO: handle just THIS combination of keys
function checkKeys(e) {
  if (status === false) {
    console.log('Start collecting data');
    console.time('dataCollectingTime');
    document.addEventListener('click', printMousePos);
    status = true;
  } else {
    console.log('End collecting data');
    document.removeEventListener('click', printMousePos);
    console.timeEnd('dataCollectingTime');
    status = false;
  }
}
 
document.addEventListener('keypress', checkKeys);