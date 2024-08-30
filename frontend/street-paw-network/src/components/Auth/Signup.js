import React, { useState,useEffect } from "react";
import authService from "../../services/authService";

function Signup() {
  const [signinData, setsigninData] = useState({
    username: "",
    password: "",
    email: "",
    roles: [],
  });
  const [message, setMessage] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    if (token) {
      const user = JSON.parse(localStorage.getItem("user"));
      const roles = user.roles || [];
      setIsAdmin(roles.includes("ROLE_ADMIN"));
    }
  }, []);

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem("accessToken");
      await authService.signup(signinData, token, isAdmin);
      setMessage("Registration successful! You can now log in.");
    } catch (error) {
      setMessage("Failed to register. Please try again.");
    }
  };

  return (
    <div>
      <h2>Sign Up</h2>
      <form onSubmit={handleSignup}>
        <input
          type="text"
          placeholder="Username"
          value={signinData.username}
          onChange={(e) =>
            setsigninData({ ...signinData, username: e.target.value })
          }
        />
        <input
          type="email"
          placeholder="Email"
          value={signinData.email}
          onChange={(e) =>
            setsigninData({ ...signinData, email: e.target.value })
          }
        />
        <input
          type="password"
          placeholder="Password"
          value={signinData.password}
          onChange={(e) =>
            setsigninData({ ...signinData, password: e.target.value })
          }
        />
        <div>
          <select
            multiple
            value={signinData.roles}
            onChange={(e) =>
              setsigninData({
                ...signinData,
                roles: Array.from(
                  e.target.selectedOptions,
                  (option) => option.value
                ),
              })
            }
          >
            <option value="user">User</option>
            {isAdmin && <option value="admin">Admin</option>}
          </select>
        </div>
        <button type="submit">Sign Up</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default Signup;
