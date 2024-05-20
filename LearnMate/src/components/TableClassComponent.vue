<template>
    <div class="row">
      <div class="col-12 grid-margin">
        <div class="card">
          <div class="card-body">
            <h4 class="card-title">Clases Particulares</h4>
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr>
                    <th> Profesor/Estudiante* </th>
                    <th> Asignatura </th>
                    <th> Fecha </th>
                    <th> Hora </th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <img src="../assets/profesora3.png" class="me-2" alt="image"> David Grey
                    </td>
                    <td class="asignatura"> <p>Química</p></td>
                    <td> 22/05/2024</td>
                    <td> 17:30 </td>
                  </tr>
                  <tr>
                    <td>
                      <img src="../assets/laptop-man-woman.jpg" class="me-2" alt="image"> Stella Johnson
                    </td>
                    <td class="asignatura"> <p>Historia</p> </td>
                    <td> 10/05/2024</td>
                    <td> 16:00 </td>
                  </tr>
                  <tr>
                    <td>
                      <img src="../assets/asian-woman.jpg" class="me-2" alt="image"> Marina Michel
                    </td>
                    <td class="asignatura"> <p>Matemáticas</p> </td>
                    <td> 16/08/2024</td>
                    <td> 12:00 </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <style lang="scss" scoped>
  .table {
    width: 100%;
    border-collapse: collapse;
    border-spacing: 0;
    
    th, td {
      padding: 8px;
      text-align: left;
      border-bottom: 1px solid #dee2e6;
    }
    
    th {
      font-weight: bold;
    }
  }
  
  .me-2 {
    width: 40px;
    height: 40px;
    object-fit: cover;
    border-radius: 50%;
  }
  
  .asignatura p {
    margin: 0;
  }
  
  .card {
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    
    .card-body {
      padding: 20px;
    }
    
    .card-title {
      margin-bottom: 15px;
    }
  }
  </style>
  