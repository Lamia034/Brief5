const amountInput = document.querySelector("input[type='number']");
const amountDisplayer = document.querySelector(".amount");
const monthsRange = document.querySelector(".range-input");
const monthsDisplayer = document.querySelectorAll(".months");
const totalDisplayer = document.querySelector(".total");
const backBtn = document.querySelectorAll(".backTo");
const form1 = document.querySelector(".form1");
const form2 = document.querySelector(".form2");
const form3 = document.querySelector(".form3");
let toggleState = 1;
let total = 0;
const interest = 7;
let month = 4;
const nextBtn = document.querySelector(".next");
const value = document.querySelector("#value");
const duration = document.querySelector("#duration");
duration.value = month;
console.log("Script loaded"); // Verify that the script is loaded.

monthsRange.addEventListener("input", (e)=>{
    for(let tmp of monthsDisplayer){
        month = Number.parseInt(e.target.value);
        tmp.innerText = month;
        duration.value = month;
    }
    if(total >= 1000 )
        totalDisplayer.innerText = calcule(total) + " dh";

});

amountInput.addEventListener("input", (e)=>{
    total = Number.parseInt(e.target.value);
    amountDisplayer.innerText = total + " dh";
    value.value = total;
    totalDisplayer.innerText = calcule(total) + " dh";
});

backBtn.forEach(btn =>{
    btn.addEventListener('click', (e)=>{
        console.log("clicked");
        if(toggleState == 1 && total >= 1000){
            form1.classList.remove("hidden");
            form2.classList.remove("hidden");
            form3.classList.add("hidden");
            toggleState = 2;
        }else if(toggleState == 2){
            form1.classList.add("hidden");
            form2.classList.add("hidden");
            form3.classList.remove("hidden");
            toggleState = 1;
        }
    });
});

const calcule = (value)=>{
    let a = value * (interest /12);
    let b = 1 - Math.pow((1+(interest/12)), -month);
    return a/b;
};