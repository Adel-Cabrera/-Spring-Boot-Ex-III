package cl.angel.tdd;

public class EmpBusinessLogic {
	   // Calculate the yearly salary of employee
	   public double calculateYearlySalary(EmployeeDetails employeeDetails) {
	      double yearlySalary = 0;
	      yearlySalary = employeeDetails.getMonthlySalary() * 12;
	      return yearlySalary;
	   }
		
	   // Calculate the appraisal amount of employee
	   public double calculateAppraisal(EmployeeDetails employeeDetails) {
	      double appraisal = 0;
			
	      if(employeeDetails.getMonthlySalary() < 10000){
	         appraisal = 500;
	      }else{
	         appraisal = 1000;
	      }
	      
	      if (employeeDetails.getAge()>30)
	    	  appraisal = 2000;
	      
	      if (employeeDetails.getName().equals("Pedro")) {
	    	  System.out.println("Enviando correo a Pedro");
	      }
	      		
	      return appraisal;
	   }
	}