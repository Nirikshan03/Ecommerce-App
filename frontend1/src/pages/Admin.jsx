import React, { useState } from "react";
import "../comp_css/Admin.css";
import AddProduct from "../components/AddProduct";
import AdminUserDetails from "../components/AdminUserDetails";
import AllOrderAdmin from "../components/AllOrderAdmin";
import AllProductAdmin from "../components/AllProductAdmin";
import { useNavigate } from "react-router-dom";

const Admin = () => {
  const [selectedComponent, setSelectedComponent] = useState("all-products");
  const navigate = useNavigate();

  const renderSelectedComponent = () => {
    switch (selectedComponent) {
      case "add-product":
        return <AddProduct />;
      case "all-orders":
        return <AllOrderAdmin />;
      case "all-customers":
        return <AdminUserDetails />;
      case "all-products":
      default:
        return <AllProductAdmin />;
    }
  };

  return (
    <>
      <div className="admin-navbar">
        <h3 onClick={() => setSelectedComponent("all-products")}>
          Admin Home
        </h3>
        <h1 style={{ textAlign: "center", color: "blue" }}>ADMIN PAGE</h1>
        <h3
          onClick={() => {
            localStorage.removeItem("adminid");
            localStorage.removeItem("jwtToken");
            navigate("/admin-login");
          }}
        >
          Logout
        </h3>
      </div>

      <div className="admincontainer">
        <div className="productConatiner">{renderSelectedComponent()}</div>
        <div className="boxConatiner">
          <ul>
            <li onClick={() => setSelectedComponent("all-products")}>
              All Products
            </li>
            <li onClick={() => setSelectedComponent("add-product")}>
              Add New Product
            </li>
            <li onClick={() => setSelectedComponent("all-orders")}>
              View All Orders
            </li>
            <li onClick={() => setSelectedComponent("all-customers")}>
              View All Customers
            </li>
          </ul>
        </div>
      </div>
    </>
  );
};

export default Admin;
