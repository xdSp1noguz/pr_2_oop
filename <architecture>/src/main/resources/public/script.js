const API_URL = "https://urban-acorn-p4q5qrxxv4vh66w6-8080.app.github.dev";
fetch(API_URL + "/api/models").then(r=>r.json()).then(d=>{
document.getElementById("models-list").innerHTML = d.map(m=>`<div>${m.modelid}</div>`).join("")
});
