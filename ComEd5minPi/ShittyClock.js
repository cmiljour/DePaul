class ShittyClock extends HTMLElement {
    constructor(){
      super();

      const shadowRoot = this.attachShadow({mode: 'open'});
      shadowRoot.innerHTML = `
        <style>
        :host {
        padding-top: 55px;
        text-align:center;
        font-size: 800%
        } 
        </style>
        <slot></slot>
      `
    }
    
    
}

window.customElements.define('shitty-clock', ShittyClock);

function startTime () {
    var date = new Date();
    var h = date.getHours(); // 0 - 23
    var m = date.getMinutes(); // 0 - 59
    var s = date.getSeconds(); // 0 - 59
    
    if(h == 0){
        h = 12;
    }
    
    if(h > 12){
        h = h - 12;
    }
    
    //h = (h < 10) ? "0" + h : h;
    m = (m < 10) ? "0" + m : m;
    s = (s < 10) ? "0" + s : s;
    
    var time = h + ":" + m + ":" + s

   document.getElementById('timer').innerHTML = time;
   var t = setTimeout(this.startTime, 500);
}

startTime()





  
  
  
  