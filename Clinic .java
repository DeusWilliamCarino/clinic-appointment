import javax.swing.*;

public class Clinic  {
    public static void main(String[]args){
        ClinicAppointment c=new ClinicAppointment();
        String[] menu={"Add Patient","Next Patient","Move Patient","Swap Patients","Cancel Appointment","End Day",
                "Display Today's Patients","Exit"};
        String status,option;
        do{
            c.setMonth("June");
            c.merge();
            status= "Clinic opens at 9:00am to 7:00pm.\n10 patients per day only\n\n"+c.display();
            JTextArea textArea = new JTextArea (10,20);
            textArea.setText(status);
            textArea.setEditable(false);
            JScrollPane scroll = new JScrollPane (textArea);
            option= JOptionPane.showInputDialog(null,scroll,
                    "Complexity Bois Clinic",1,null,menu,menu[0]).toString();

            switch (option){
                case"Add Patient":
                    c.addPatient();
                    break;
                case"Next Patient":
                    c.checkOutPatient();
                    break;
                case "Move Patient":
                    c.movePatient();
                    break;
                case"Swap Patients":
                    c.swapPatientsInDay();
                    break;
                case"Cancel Appointment":
                    c.cancelPatient();
                    break;
                case"Display Today's Patients":
                    c.displayToday();
                    break;
                case"End Day":
                    c.endDay();
                    break;
            }
        }while (!option.equals("Exit"));

    }
}
