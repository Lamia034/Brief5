<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="WEB-INF/assets/header.jsp"%>
<div class="flex justify-center items-center mt-10">
  <div class="w-[500px] rounded-sm p-4 bg-white">
    <form action="${pageContext.servletContext.contextPath}/client" method="POST" class="w-full flex flex-col justify-center items-start gap-3">
      <p class="text-center w-full font-bold">Formulaire d'ajout</p>
      <div class="flex flex-col justify-center items-start w-full">
        <label for="firstName" class="font-semibold">Nom</label>
        <input required type="text" name="firstName" id="firstName" value="" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
      </div>
      <div class="flex flex-col justify-center items-start w-full">
        <label for="lastName" class="font-semibold">Prénom</label>
        <input required type="text" name="lastName" id="lastName" value="" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
      </div>
      <div class="flex flex-col justify-center items-start w-full">
        <label for="birthDay" class="font-semibold">Date de naissance</label>
        <input required type="date" name="birthDay" id="birthDay" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
      </div>
      <div class="flex flex-col justify-center items-start w-full">
        <label for="phone" class="font-semibold">Téléphone</label>
        <input required type="text" name="phone" id="phone" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
      </div>
      <div class="flex flex-col justify-center items-start w-full">
        <label for="address" class="font-semibold">Adresse</label>
        <textarea required name="address" id="address" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1"></textarea>
      </div>
      <div class="flex flex-col justify-center items-start w-full">
        <label for="employee" class="font-semibold">Employee</label>
        <select required id="employee" name="employee" class=" p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
          <c:forEach items="${emploies}" var="employee">
            <option value="${employee.registrationNbr}">${employee.firstName} ${employee.lastName}</option>
          </c:forEach>
        </select>
      </div>
      <div class="flex flex-col justify-center items-start w-full">
        <label for="agency" class="font-semibold">Agence</label>
        <select required id="agency" name="agency" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
          <c:forEach items="${agencies}" var="agency">
            <option value="${agency.code}">${agency.name}</option>
          </c:forEach>
        </select>
      </div>
      <div class="flex w-full justify-center items-center gap-2">
        <button class="w-full bg-[#01B062] p-4 text-white rounded-md">Ajouter Client</button>
      </div>
    </form>
  </div>
</div>
<script>
  if(${created})
    Swal.fire({
      title: "client ajouter avec success",
      icon: "success",
      showCancelButton: true
    })
  else
    Swal.fire({
      title: "un des valeur est invalide",
      icon: "error",
      showCancelButton: true
    })
</script>
<%@ include file="WEB-INF/assets/footer.jsp"%>