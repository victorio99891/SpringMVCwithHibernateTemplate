console.log("colorSortingArrows.js script loaded");
console.log(window.location.href);

function colorArrow() {
    var url_address = window.location.href;

    if (url_address.includes("first_name")) {
        if (url_address.includes("asc")) {
            addStyle("firstNameArrowUp");
        } else if (url_address.includes("desc")) {
            addStyle("firstNameArrowDown")
        }
    } else if (url_address.includes("last_name")) {
        if (url_address.includes("asc")) {
            addStyle("lastNameArrowUp");
        } else if (url_address.includes("desc")) {
            addStyle("lastNameArrowDown")
        }
    } else if (url_address.includes("email")) {
        if (url_address.includes("asc")) {
            addStyle("emailArrowUp");
        } else if (url_address.includes("desc")) {
            addStyle("emailArrowDown")
        }
    }
}


function addStyle(elementId) {
    document.getElementById(elementId).style.borderColor = "FFF82A";
    document.getElementById(elementId).style.borderWidth = "0px 10px 10px 0px"
}

window.onload = colorArrow();