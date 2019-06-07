function getFileData(inputFile) {
    const temporaryFileReader = new FileReader();
    return new Promise((resolve, reject) => {
        temporaryFileReader.onerror = () => {
            temporaryFileReader.abort();
            reject(new DOMException("Problem parsing input file."));
        };
        temporaryFileReader.addEventListener("load", function () {
            var data = {
                content: temporaryFileReader.result.split(',')[1]
            };
            resolve(data);
        }, false);
        let control = document.getElementById(inputFile);
        temporaryFileReader.readAsDataURL(control.files[0]);
    });
};

function saveAsFile(filename, bytesBase64) {
    var link = document.createElement('a');
    link.download = filename;
    link.href = "data:application/octet-stream;base64," + bytesBase64;
    document.body.appendChild(link); // Needed for Firefox
    link.click();
    document.body.removeChild(link);
}

//Blazor.registerFunction("getFileData", function (inputFile) {
//    return readUploadedFileAsText(inputFile);
//});