/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flex;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
//import javax.mail.*;
/**
 *
 * @author SHARON
 */
public class Flex extends javax.swing.JFrame{

    private static final String userName = "root";
    private static final String password = "sharonsimon";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/stddb";
    
    
    Connection sqlConnect = null;
    PreparedStatement prepStat = null;
    ResultSet rsltSet = null;
    ResultSet rsltSetcount = null;
    /**
     * Creates new form Flex
     */
    public Flex() {
        initComponents();
        updateSystem();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    //*********FUNCTIONS************
    public void updateSystem(){
        
        int x,i;
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("select * from studentdb");
            
            rsltSet = prepStat.executeQuery();
            ResultSetMetaData rsltMeta = rsltSet.getMetaData();
            
            x = rsltMeta.getColumnCount();
            
            DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
            tableau.setRowCount(0);
            
            while(rsltSet.next()){
                
                Vector columnData = new Vector();
                for(i = 1; i<=x; i++)
                {
                 columnData.add(rsltSet.getString("adminNo"));
                 columnData.add(rsltSet.getString("surname"));
                 columnData.add(rsltSet.getString("firstName"));
                 columnData.add(rsltSet.getString("Gender"));
                }
                tableau.addRow(columnData);
                Count();
                Countf();
                totalCount();
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }
    public void Count(){
         
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("SELECT COUNT(*) FROM studentdb WHERE gender = ?");
            prepStat.setString(1, "Male");
            rsltSetcount = prepStat.executeQuery();
            rsltSetcount.next();
            txtMaleStudents.setText(String.valueOf(rsltSetcount.getInt(1)));
            txtMaleStudents.setEditable(false);
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void view(){
        DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        int selectedRows = jTable1.getSelectedRow();
        
        try {
            int id = Integer.parseInt(tableau.getValueAt(selectedRows, 0).toString());
            String fname,lname,Fname,Mname,gender,age,city,phone,email;
            byte[] photo;
            viewDetails vd = new viewDetails();
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("SELECT * FROM studentdb WHERE adminNo = ?");
            prepStat.setInt(1, id);
            rsltSet = prepStat.executeQuery();
            rsltSet.next();
            lname = rsltSet.getString(2);
            fname = rsltSet.getString(3);
            gender = rsltSet.getString(4);
            Fname = rsltSet.getString(5);
            Mname = rsltSet.getString(6);
            city = rsltSet.getString(7);
            age = rsltSet.getString(8);
            phone = rsltSet.getString(9);
            email = rsltSet.getString(10);
            photo = rsltSet.getBytes(11);
            ImageIcon image = new ImageIcon(photo);
            viewDetails.txtAdminNoRD.setText(String.valueOf(id));
            viewDetails.txtAdminNoRD.setEditable(false);
            viewDetails.txtLastNameRD.setText(lname);
            viewDetails.txtLastNameRD.setEditable(false);
            viewDetails.txtFirstNameRD.setText(fname);
            viewDetails.txtFirstNameRD.setEditable(false);
            viewDetails.txtGenderRD.setText(gender);
            viewDetails.txtGenderRD.setEditable(false);
            viewDetails.txtFatherNameRD.setText(Fname);
            viewDetails.txtFatherNameRD.setEditable(false);
            viewDetails.txtMotherNameRD.setText(Mname);
            viewDetails.txtMotherNameRD.setEditable(false);
            viewDetails.txtCityRD.setText(city);
            viewDetails.txtCityRD.setEditable(false);
            viewDetails.txtAgeRD.setText(age);
            viewDetails.txtAgeRD.setEditable(false);
            viewDetails.txtPhoneRD.setText(phone);
            viewDetails.txtPhoneRD.setEditable(false);
            viewDetails.txtEmailRD.setText(email);
            viewDetails.txtEmailRD.setEditable(false);
            viewDetails.lblPhotoRD.setIcon(image);
            vd.setVisible(true);   
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
    public void edit(){
        DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        int selectedRows = jTable1.getSelectedRow();
        
        try {
            int id = Integer.parseInt(tableau.getValueAt(selectedRows, 0).toString());
            String fname,lname,Fname,Mname,gender,age,city,phone,email;
            byte[] photo;
            Editable edit = new Editable();
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("SELECT * FROM studentdb WHERE adminNo = ?");
            prepStat.setInt(1, id);
            rsltSet = prepStat.executeQuery();
            rsltSet.next();
            lname = rsltSet.getString(2);
            fname = rsltSet.getString(3);
            gender = rsltSet.getString(4);
            Fname = rsltSet.getString(5);
            Mname = rsltSet.getString(6);
            city = rsltSet.getString(7);
            age = rsltSet.getString(8);
            phone = rsltSet.getString(9);
            email = rsltSet.getString(10);
            photo = rsltSet.getBytes(11);
            ImageIcon image = new ImageIcon(photo);
            Editable.txtAdminNoE.setText(String.valueOf(id));
            Editable.txtAdminNoE.setEditable(false);
            Editable.txtLastNameE.setText(lname);
            Editable.txtLastNameE.setEditable(true);
            Editable.txtFirstNameE.setText(fname);
            Editable.txtFirstNameE.setEditable(true);
            Editable.cboGenderE.setSelectedItem(gender);
            Editable.txtFatherNameE.setText(Fname);
            Editable.txtFatherNameE.setEditable(true);
            Editable.txtMotherNameE.setText(Mname);
            Editable.txtMotherNameE.setEditable(true);
            Editable.txtCityE.setText(city);
            Editable.txtCityE.setEditable(true);
            Editable.txtAgeE.setText(age);
            Editable.txtAgeE.setEditable(true);
            Editable.txtPhoneE.setText(phone);
            Editable.txtPhoneE.setEditable(true);
            Editable.txtEmailE.setText(email);
            Editable.txtEmailE.setEditable(true);
            Editable.lblPhotoE.setIcon(image);
            edit.setVisible(true);
            this.hide();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
    }
    public void email(){
         DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        
        int selectedRows = jTable1.getSelectedRow();
        try {
            int id = Integer.parseInt(tableau.getValueAt(selectedRows, 0).toString());
            String email;
            Email mail = new Email();
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("SELECT * FROM studentdb WHERE adminNo = ?");
            prepStat.setInt(1, id);
            rsltSet = prepStat.executeQuery();
            rsltSet.next();
            email = rsltSet.getString(10);
            Email.txtTo.setText(email);
            Email.txtTo.setEditable(false);
            mail.setVisible(true);
        }catch(Exception ex){
             JOptionPane.showMessageDialog(null,ex);
           // System.out.println(""+ex);
        }
        
        
    }
    public void Countf(){
         
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("SELECT COUNT(*) FROM studentdb WHERE gender = ?");
            prepStat.setString(1, "Female");
            rsltSetcount = prepStat.executeQuery();
            rsltSetcount.next();
            txtFemaleStudents.setText(String.valueOf(rsltSetcount.getInt(1)));
            txtFemaleStudents.setEditable(false);
                
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
    }    
    public void totalCount(){
         
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("SELECT COUNT(*) FROM studentdb");
            rsltSetcount = prepStat.executeQuery();
            rsltSetcount.next();
            txtTotalStudents.setText(String.valueOf(rsltSetcount.getInt(1)));
            txtTotalStudents.setEditable(false);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
        
        
    }
    /*public void add(){
        
             try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("insert into studentdb(adminNo,surname,firstName,gender,fatherName,motherName,City,Age,Phone,eMail,photo)values("
                        +"?,?,?,?,?,?,?,?,?,?,?)");
            
            
            prepStat.setString(1,Registration.txtAdminNo.getText());
            prepStat.setString(2,Registration.txtLastName.getText());
            prepStat.setString(3,Registration.txtFirstName.getText());
            prepStat.setString(4,(String)Registration.cboGender.getSelectedItem());
            prepStat.setString(5,Registration.txtFatherName.getText());
            prepStat.setString(6,Registration.txtMotherName.getText());
            prepStat.setString(7,Registration.txtCity.getText());
            prepStat.setString(8,Registration.txtAge. getText());
            prepStat.setString(9,Registration.txtPhone.getText());
            prepStat.setString(10,Registration.txtEmail.getText());
            prepStat.setBytes(11,Registration.photo);
            
            prepStat.executeUpdate();
           updateSystem();
           System.out.println("Table updated");
           JOptionPane.showMessageDialog(this,"Student registered successfully!");
           this.hide();
        }
        
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }*/
    //------------------------------
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblAdminNo = new javax.swing.JLabel();
        txtAdminNo = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        lblFirstName = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        lblSurname = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEmail = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        txtfirstName = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        txtMaleStudents = new javax.swing.JTextField();
        txtFemaleStudents = new javax.swing.JTextField();
        txtTotalStudents = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("STUDENT MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAdminNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdminNo.setText("Admission Number:");
        lblAdminNo.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel5.add(lblAdminNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, -1));

        txtAdminNo.setEditable(false);
        txtAdminNo.setBackground(new java.awt.Color(255, 255, 255));
        txtAdminNo.setPreferredSize(new java.awt.Dimension(165, 25));
        txtAdminNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminNoActionPerformed(evt);
            }
        });
        jPanel5.add(txtAdminNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        txtSurname.setEditable(false);
        txtSurname.setBackground(new java.awt.Color(255, 255, 255));
        txtSurname.setPreferredSize(new java.awt.Dimension(165, 25));
        jPanel5.add(txtSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        lblFirstName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFirstName.setText("Surname:");
        lblFirstName.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel5.add(lblFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, -1));

        txtGender.setEditable(false);
        txtGender.setBackground(new java.awt.Color(255, 255, 255));
        txtGender.setPreferredSize(new java.awt.Dimension(165, 25));
        jPanel5.add(txtGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, -1));

        lblSurname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSurname.setText("First Name:");
        lblSurname.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel5.add(lblSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 120, -1));

        lblGender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGender.setText("Gender:");
        lblGender.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel5.add(lblGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 120, -1));

        btnExit.setBackground(new java.awt.Color(204, 204, 204));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.setBorderPainted(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel5.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 90, 20));

        btnAdd.setBackground(new java.awt.Color(204, 204, 204));
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.setBorderPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel5.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 90, 20));

        btnEmail.setBackground(new java.awt.Color(204, 204, 204));
        btnEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEmail.setText(" EMAIL");
        btnEmail.setBorderPainted(false);
        btnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailActionPerformed(evt);
            }
        });
        jPanel5.add(btnEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 90, 20));

        btnEdit.setBackground(new java.awt.Color(204, 204, 204));
        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEdit.setText("EDIT");
        btnEdit.setBorderPainted(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel5.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 90, 20));

        btnView.setBackground(new java.awt.Color(204, 204, 204));
        btnView.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnView.setText("VIEW");
        btnView.setBorderPainted(false);
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        jPanel5.add(btnView, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 90, 20));

        txtfirstName.setEditable(false);
        txtfirstName.setBackground(new java.awt.Color(255, 255, 255));
        txtfirstName.setPreferredSize(new java.awt.Dimension(165, 25));
        jPanel5.add(txtfirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        btnDelete.setBackground(new java.awt.Color(204, 204, 204));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.setBorderPainted(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 90, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 360));

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AdmissionNumber", "Surname", "FirstName", "Gender"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 650, 220));

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaleStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaleStudentsActionPerformed(evt);
            }
        });
        jPanel7.add(txtMaleStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 30));

        txtFemaleStudents.setAutoscrolls(false);
        txtFemaleStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFemaleStudentsActionPerformed(evt);
            }
        });
        jPanel7.add(txtFemaleStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 80, 30));

        txtTotalStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalStudentsActionPerformed(evt);
            }
        });
        jPanel7.add(txtTotalStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 80, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Male Students:");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Female Students:");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Total Students:");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 650, 110));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 670, 360));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private JFrame frame;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        frame = new JFrame("Exit");
        if(JOptionPane.showConfirmDialog(frame,"Do you want to quit?","Student Management System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Registration reg = new Registration();
        reg.setVisible(true);
        this.hide();
        /*
        try
        {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("insert into studentdb(adminNo,surname,firstName,Gender)values("
                        +"?,?,?,?)");
            
            
            prepStat.setString(1,txtAdminNo.getText());
            prepStat.setString(2,txtSurname.getText());
            prepStat.setString(3,txtfirstName.getText());
            prepStat.setString(4, (String)cboGender.getSelectedItem());
            
            prepStat.executeUpdate();
            JOptionPane.showMessageDialog(this,"Student record added!");
            updateSystem();
        }
        
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_btnAddActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        
        int selectedRows = jTable1.getSelectedRow();
        
        txtAdminNo.setText(tableau.getValueAt(selectedRows,0).toString());
        txtSurname.setText(tableau.getValueAt(selectedRows,1).toString());
        txtfirstName.setText(tableau.getValueAt(selectedRows,2).toString());
        txtGender.setText(tableau.getValueAt(selectedRows,3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        
        int selectedRows = jTable1.getSelectedRow();
        edit();
        
        /*DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        int selectedRows = jTable1.getSelectedRow();
        
        try
        {
            int id = Integer.parseInt(tableau.getValueAt(selectedRows, 0).toString());
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("update studentdb set surname=?, firstName=?, gender=? where adminNo =?" );
            
            
            prepStat.setString(1,txtSurname.getText());
            prepStat.setString(2,txtfirstName.getText());
            prepStat.setString(3, (String)cboGender.getSelectedItem());
            prepStat.setInt(4,id);
            prepStat.executeUpdate();
            JOptionPane.showMessageDialog(this,"Student record updated!");
            updateSystem();
        }
        
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }                        */          
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailActionPerformed
        DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        
        int selectedRows = jTable1.getSelectedRow();
        email();
    }//GEN-LAST:event_btnEmailActionPerformed

    private void txtMaleStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaleStudentsActionPerformed
        
    }//GEN-LAST:event_txtMaleStudentsActionPerformed

    private void txtFemaleStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFemaleStudentsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFemaleStudentsActionPerformed

    private void txtTotalStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalStudentsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalStudentsActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        
        int selectedRows = jTable1.getSelectedRow();
        view();
    }//GEN-LAST:event_btnViewActionPerformed

    private void txtAdminNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminNoActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DefaultTableModel tableau = (DefaultTableModel)jTable1.getModel();
        int selectedRows = jTable1.getSelectedRow();
        
        try
        {
            int id = Integer.parseInt(tableau.getValueAt(selectedRows, 0).toString());
            
            int delete = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?","Warning",JOptionPane.YES_NO_OPTION);
            
            if(delete == JOptionPane.YES_OPTION)
            {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConnect = DriverManager.getConnection(dataConnect,userName,password);
            prepStat = sqlConnect.prepareStatement("delete from studentdb where adminNo =?" );
            prepStat.setInt(1, id);
            prepStat.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student record deleted");
            updateSystem();
            txtAdminNo.setText("");
            txtAdminNo.requestFocus();
            txtSurname.setText("");
            txtfirstName.setText("");
            txtGender.setText("");
            }
        }
        
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }           
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Flex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Flex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEmail;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAdminNo;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JTextField txtAdminNo;
    private javax.swing.JTextField txtFemaleStudents;
    public static javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtMaleStudents;
    public static javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtTotalStudents;
    public static javax.swing.JTextField txtfirstName;
    // End of variables declaration//GEN-END:variables
}
