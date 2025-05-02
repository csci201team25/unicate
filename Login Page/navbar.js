window.addEventListener("DOMContentLoaded", () => {
    const nav = document.querySelector(".navbar-right");
    if (!nav) return;
  
    nav.innerHTML = "";
  
    const calendarLink = document.createElement("a");
    calendarLink.href = "Calendar.html";
    calendarLink.className = "nav-link";
    calendarLink.textContent = "Calendar";
    nav.appendChild(calendarLink);
  
    const isLoggedIn = localStorage.getItem("login") === "yes";
  
    if (isLoggedIn) {
      const logoutBtn = document.createElement("a");
      logoutBtn.href = "#";
      logoutBtn.className = "nav-link outlined";
      logoutBtn.textContent = "Logout";
      logoutBtn.addEventListener("click", () => {
        localStorage.clear();
        window.location.href = "login.html";
      });
      nav.appendChild(logoutBtn);
    } else {
      const loginBtn = document.createElement("a");
      loginBtn.href = "login.html";
      loginBtn.className = "nav-link outlined";
      loginBtn.textContent = "Sign In / Sign Up";
      nav.appendChild(loginBtn);
    }
  });
  