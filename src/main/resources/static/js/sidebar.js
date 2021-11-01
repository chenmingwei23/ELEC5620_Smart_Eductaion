function createNewElement() {
  // First create a DIV element.
  var extraContact = document.createElement('divMessage');
  var txtNewInputBox = document.createElement('div1');
  var txtNewInputBox2 = document.createElement('div2');
  var txtNewInputBox3 = document.createElement('div3');
  var txtNewInputBox4 = document.createElement('div4');
  var txtNewInputBox5 = document.createElement('div5');
  var txtNewInputBox6 = document.createElement('div6');
  var txtNewInputBox7 = document.createElement('div7');
  var txtNewInputBox8 = document.createElement('div8');


  // Then add the content (a new input box) of the element.
  var ContactOutput = document.createTextNode('Manager Info');
  txtNewInputBox.innerHTML = "<input type='text' class='form-control' placeholder='First Name' id='first_Name' required>";
  txtNewInputBox2.innerHTML = "<input type='text' class='form-control' placeholder='Last Name' id='last_Name' required>";
  txtNewInputBox3.innerHTML = "<input type='text' class='form-control' placeholder='Other Name' id='other_Name'>";
  txtNewInputBox4.innerHTML = "<input type='email' class='form-control' placeholder='Email Address' id='email' required>";
  txtNewInputBox5.innerHTML = "<input type='password' class='form-control' placeholder='Password' id='password' pattern=\'(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}' required>";
  txtNewInputBox7.innerHTML = "<input type='password' class='form-control' placeholder='Confirm Password' id='password_confirm' pattern=\'(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}' required>";
  txtNewInputBox8.innerHTML = "<div class='col text-center'> <button type=\"submit\" class=\"btn btn-primary btn-lg btn-warning\" >CREATE ACCOUNT <span class=\"glyphicon glyphicon-send\"></span></button></div>";

  // Finally put it where it is supposed to appear.
  document.getElementById("newElementId").appendChild(ContactOutput);
  document.getElementById("newElementId").appendChild(txtNewInputBox);
  document.getElementById("newElementId").appendChild(txtNewInputBox2);
  document.getElementById("newElementId").appendChild(txtNewInputBox3);
  document.getElementById("newElementId").appendChild(txtNewInputBox4);
  document.getElementById("newElementId").appendChild(txtNewInputBox5);
  document.getElementById("newElementId").appendChild(txtNewInputBox6);
  document.getElementById("newElementId").appendChild(txtNewInputBox7);
  document.getElementById("newElementId").appendChild(txtNewInputBox8);
}

function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
}

function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
}


$(document).ready(function() {

  $("#collapse").on("click", function() {

    $("#sidebar").toggleClass("active");
    $(".fa-align-left").toggleClass("fa-chevron-circle-right");
  })
})

/* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
function openNav() {
  document.getElementById("mySidebar").style.width = "200px";
  document.getElementById("main").style.marginLeft = "200px";
}

/* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}

function openMsg() {
  document.getElementById("myMsgbar").style.width = "380px";
  document.getElementById("main").style.marginLeft = "380px";
}

/* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
function closeMsg() {
  document.getElementById("myMsgbar").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}






