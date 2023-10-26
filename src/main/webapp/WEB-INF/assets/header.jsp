<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>EasyBank</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="${pageContext.servletContext.contextPath}/src/dist/output.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.8.1/flowbite.min.css" rel="stylesheet" />
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body class="bg-[#EEF1F6] flex w-screen h-screen font-poppins flex flex-col">
<nav class="bg-green-700 z-50 border-green-700 h-16 flex justify-evenly md:items-center px-6 py-4 w-screen">
  <div class="container mx-auto flex flex-wrap justify-between items-center md:justify-center ">
    <a href="#" class="flex md:items-center">
      <span class=""><img class="h-10 w-15 pr-10 " src="${pageContext.servletContext.contextPath}/src/images/banklogo.png" alt="logo"></span>
    </a>
    <button data-collapse-toggle="mobile-menu" type="button" class="md:hidden ml-3 text-gray-400 hover:text-gray-900 focus:outline-none focus:ring-2 focus:ring-blue-300 rounded-lg inline-flex items-center justify-center" aria-controls="mobile-menu-2" aria-expanded="false">
      <span class="sr-only">Open main menu</span>
      <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg>
      <svg class="hidden w-6 h-6" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
    </button>
    <div class="hidden z-50 bg-green-700 md:block w-full md:w-auto" id="mobile-menu">
      <ul class="flex-col justify-end md:flex-row flex md:space-x-20 mt-4 md:mt-0 md:text-lg md:font-medium">
        <li>
          <a href="${pageContext.servletContext.contextPath}/" class="  text-white block pl-3 pr-4 py-2 md:white md:p-2 rounded hover:text-green-700 rounded p-3 hover:bg-white focus:outline-none" aria-current="page">Home</a>
        </li>

        <li>
          <a href="${pageContext.servletContext.contextPath}/ListCredits"  class="text-white hover:bg-green-300 border-b border-gray-100  md:border-0 block pl-3 pr-4 py-2 hover:text-green-700 rounded p-3 hover:bg-white md:p-2">List Credits</a>
        </li>

        <li>
          <a href="${pageContext.servletContext.contextPath}/client"  class="text-white hover:bg-green-300 border-b border-gray-100  md:border-0 block pl-3 pr-4 py-2 hover:text-green-700 rounded p-3 hover:bg-white md:p-2">Add Client</a>
        </li>

        <li>
          <a href="#" class="text-white border-b border-gray-100  md:border-0 block pl-3 pr-4 py-2 hover:text-green-700 rounded p-3 hover:bg-white md:p-2">Contact</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
