import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.util.*;


public class ClinicAppointment {
    private final int maxdays=7;
    private final int maxPatient=10;
    private int globalTimer=0;
    private String allList="";
    private String month;
    ArrayList<ArrayList<String>> clinic;

    public ClinicAppointment(){
        clinic = new ArrayList<>(maxdays);
        for(int i=0; i < maxdays; i++) {
            clinic.add(new ArrayList(maxPatient));
        }
    }

    private boolean isFull(int day){
        return clinic.get(day).size() == maxPatient;
    }

    public void endDay(){
        globalTimer++;
    }

    public void setMonth(String m){month=m;}

    private String dateToday(){
        return month+" "+globalTimer+1;
    }

    public void addPatient(){
        String name =JOptionPane.showInputDialog("What is the name of the patient");
        int day=Integer.parseInt(JOptionPane.showInputDialog("In the 7 days of the week, when will the " +
                "patient be meeting the doctor"));

        if (isFull(day-1)){
            JOptionPane.showMessageDialog(null,"The day is fully booked");
        }else {
            if (day < globalTimer + 1) {
                JOptionPane.showMessageDialog(null, "Cannot add patients to the days before today");
            } else {
                clinic.get(day - 1).add(name);
            }
        }

    }

    public void movePatient(){
        String name =JOptionPane.showInputDialog("What is the name of the patient");
        int day=Integer.parseInt(JOptionPane.showInputDialog("In the 7 days of the week, when will the " +
                "patient be meeting the doctor"));
        if (isFull(day-1)){
            JOptionPane.showMessageDialog(null,"The day is fully booked");
        }else {
            if (day < globalTimer + 1) {
                JOptionPane.showMessageDialog(null, "Cannot add patients to the days before today");
            } else {
                cancelPatient(name);
                clinic.get(day - 1).add(name);
            }
        }
    }

    public void cancelPatient(String name){
        name=name.toLowerCase();
        String placer;
        for(int i=0;i<clinic.size();i++){
            for (int c=0;c<clinic.get(i).size();c++){
                placer=clinic.get(i).get(c).toLowerCase();
                if(placer.equals(name)){
                    clinic.get(i).remove(c);
                }
            }
        }

    }

    public void cancelPatient(){
        String name= JOptionPane.showInputDialog("What is the name of the Patient");
        name=name.toLowerCase();
        String placer;
        for(int i=0;i<clinic.size();i++){
            for (int c=0;c<clinic.get(i).size();c++){
                placer=clinic.get(i).get(c).toLowerCase();
                if(placer.equals(name)){
                    clinic.get(i).remove(c);
                }
            }
        }
    }

    public void checkOutPatient(){
        clinic.get(globalTimer).remove(0);
    }

    public void swapPatientsInDay(){
        String first= JOptionPane.showInputDialog("What is the name of the Patient");
        first=first.toLowerCase();
        String second=JOptionPane.showInputDialog("What is the name of the Patient to be swapped");
        second=second.toLowerCase();
        String placer;
        int indeX=0,indexY=0;
        for (int c=0;c<clinic.get(globalTimer).size();c++){
            placer=clinic.get(globalTimer).get(c).toLowerCase();
            if(placer.equals(first)){
                indeX=c;
            }
            if(placer.equals(second)){
                indexY=c;
            }
        }
        Collections.swap(clinic.get(globalTimer),indeX,indexY);
    }

    public void merge(){
        int counter=1;
        String hold="";
        for(int i=0;i<clinic.size();i++){
            if(i<globalTimer){
                hold+=month+" "+counter+":Day Done\n";
            }else{
                hold+=month+" "+counter+"\n";
            }
            counter++;
            int patientCounter=1;
            int time=9;
            Object placer="";
            Queue q=new LinkedList();
            for (int g=0;g<clinic.get(i).size();g++) {
                q.add(clinic.get(i).get(g));
            }
            for (int c=0;c<clinic.get(i).size();c++){
                while (!q.isEmpty()){
                    placer=q.remove();
                    if(i<globalTimer){
                        hold+= placer+"-did not come\n";
                    }else{
                        hold += "Priority "+patientCounter+" "+placer+" "+time+":00"+"\n";
                    }
                    patientCounter++;
                    if(time==12){
                        time=1;
                    }
                    time++;
                }
            }
            hold+="\n";
        }
        allList=hold;
    }

    public String display(){return allList;}

    public void displayToday(){
        String hold;
        hold=dateToday()+"\n";
        for(int i=0;i<clinic.get(globalTimer).size();i++){
            hold+=clinic.get(globalTimer).get(i)+"\n";
        }
        JTextArea textArea = new JTextArea (10,20);
        textArea.setText(hold);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null,scrollPane);
    }



}
