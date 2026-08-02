function popup() {
    alert("Welcome to Student bank !!!");
}

function trigger() {
    document.getElementById("hover").addEventListener("mouseover", popup);
}

function hideSteps() {
    document.getElementById("demo").style.display = "none";
}

document.getElementById("btnValidate").addEventListener("click", function() {
    var custId = document.getElementById("custID").value;
    var validateCust = custId.includes("TXYZ");
    var outputDiv = document.getElementById("resultOutput");

    if (custId.trim() === "") {
        outputDiv.className = "output-container error-msg";
        outputDiv.innerHTML = "Please enter a valid Customer ID";
    } else if (validateCust) {
        outputDiv.className = "output-container success-msg";
        outputDiv.innerHTML = "Customer validated successfully!";
    } else {
        outputDiv.className = "output-container error-msg";
        outputDiv.innerHTML = "Invalid customer ID";
    }
});

function generateCoupon() {
    var custId = document.getElementById("custID").value;
    var x = custId + "789456";
    var validateCust = custId.includes("TXYZ");
    var outputDiv = document.getElementById("resultOutput");

    if (validateCust) {
        outputDiv.className = "output-container success-msg";
        outputDiv.innerHTML = "Your coupon is:" + x;
    } else {
        outputDiv.className = "output-container error-msg";
        outputDiv.innerHTML = "Invalid customer ID so coupon can't be generated";
    }
}