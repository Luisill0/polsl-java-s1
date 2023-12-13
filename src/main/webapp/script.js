/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/**
 * Listener for radio button change event
 * 
 * @param value Value of the input
 * @author luiz
 */
const inputChange = (value) => {
    const button = document.getElementById("action-btn");
    if (!button) return;
    button.innerHTML=capitalizeFirstLetter(value.toLowerCase());
};

const capitalizeFirstLetter = (string) => string.charAt(0).toUpperCase() + string.slice(1);

const navigate = (route) => location.href = route;