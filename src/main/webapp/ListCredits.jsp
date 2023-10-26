<%--
  Created by IntelliJ IDEA.
  User: adm
  Date: 17/10/2023
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/assets/header.jsp" %>
<div class="flex justify-around mt-3">


  <form action="${pageContext.servletContext.contextPath}/find-date"  method="get" class="relative max-w-sm flex gap-3">
    <input name="date" type="date" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Select date">
    <button type="submit" class="p-2 text-white bg-[#1A56DB] rounded-md">chercher</button>
  </form>

  <form action="${pageContext.servletContext.contextPath}/find-status" method="get" class="flex gap-3">
    <select data-te-select-init class="rounded" name="status">
      <option value="PENDING">Pending</option>
      <option value="ACCEPTED">Accepted</option>
      <option value="REFUSED">Refused</option>
    </select>
    <button type="submit" class="p-2 text-white bg-[#1A56DB] rounded-md">chercher</button>
  </form>
</div>
<!-- Table -->
<div class=" bg-white shadow-lg pt-5 m-3 rounded-sm border border-gray-200">
  <header class="px-5 py-4 border-b border-gray-100">
    <h2 class="font-semibold text-gray-800">Credits</h2>
  </header>
  <div class="p-3">
    <div class="overflow-x-auto">
      <table class="table-auto w-full">
        <thead class="text-xs font-semibold uppercase text-gray-400 bg-gray-50">
        <tr>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-left">Client Name</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-left">Client Code</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-left">Employee number</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-center">Credit Number</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-center">Agency Code</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-center">Credit Value</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-center">Remark</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-center">Status</div>
          </th>
          <th class="p-2 whitespace-nowrap">
            <div class="font-semibold text-center">modification date/time</div>
          </th>

        </tr>
        </thead>
        <tbody class="text-sm divide-y divide-gray-100">
        <c:forEach items="${credits}" var="credit">
          <tr>
            <td class="p-2 whitespace-nowrap">
              <div class="flex items-center">
                <div class="w-10 h-10 flex-shrink-0 mr-2 sm:mr-3"><img class="rounded-full" src="https://raw.githubusercontent.com/cruip/vuejs-admin-dashboard-template/main/src/images/user-36-05.jpg" width="40" height="40" alt="Alex Shatov"></div>
                <div class="text-left pr-2">${credit.client.firstName}</div>
                <div class="text-left">${credit.client.lastName}</div>
              </div>
            </td>

            <td class="p-2 whitespace-nowrap">
              <div class="text-left">${credit.client.code}</div>
            </td>
            <td class="p-2 whitespace-nowrap">
              <div class="text-left font-medium text-green-500">${credit.employee.registrationNbr}</div>
            </td>
            <td class="p-2 whitespace-nowrap">
              <div class="text-lg text-center">${credit.id}</div>
            </td>
            <td class="p-2 whitespace-nowrap">
              <div class="text-lg text-center">${credit.agency.code}</div>
            </td>
            <td class="p-2 whitespace-nowrap">
              <div class="text-lg text-center">${credit.value}</div>
            </td>

            <td class="p-2 whitespace-nowrap">
              <div class="text-lg text-center">${credit.remark}</div>
            </td>
            <td class="p-2 whitespace-nowrap">
              <div class="text-lg text-center">${credit.status}</div>
            </td>
            <td class="p-2 whitespace-nowrap">
              <div class="text-lg text-center">${credit.modification_date} ${credit.modification_time}</div>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script>
  if(${created})
    Swal.fire({
      title: "credit ajouter avec success",
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
<script>
  // Initialization for ES Users
  import { Select, initTE } from "tw-elements";
  initTE({ Select });
</script>
<%@ include file="/WEB-INF/assets/footer.jsp" %>