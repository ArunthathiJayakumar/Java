import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Main class for the Job Portal application
public class JobPortalAWT extends Frame implements ActionListener {
    // List to hold job postings and applications
    private ArrayList<String> jobPostings = new ArrayList<>();
    private ArrayList<String> applications = new ArrayList<>();

    // GUI Components
    private Label lblTitle, lblJobTitle;
    private TextField txtJobTitle, txtApplicantName;
    private Button btnPostJob, btnViewJobs, btnApplyJob, btnViewApplications, btnClear;
    private TextArea txtOutput;

    // Constructor for setting up the Job Portal UI and functionality
    public JobPortalAWT() {
        // Setting the layout and title of the frame
        setLayout(new FlowLayout());
        setTitle("Online Job Portal");

        // Title Label
        lblTitle = new Label("Welcome to the Online Job Portal");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle);

        // Job Title Input
        lblJobTitle = new Label("Enter Job Title:");
        txtJobTitle = new TextField(20);
        add(lblJobTitle);
        add(txtJobTitle);

        // Buttons for functionality
        btnPostJob = new Button("Post Job");
        btnViewJobs = new Button("View Jobs");
        btnApplyJob = new Button("Apply for Job");
        btnViewApplications = new Button("View Applications");
        btnClear = new Button("Clear Output");

        add(btnPostJob);
        add(btnViewJobs);
        add(btnApplyJob);
        add(btnViewApplications);
        add(btnClear);

        // Applicant Name Input (for job applications)
        Label lblApplicantName = new Label("Applicant Name:");
        txtApplicantName = new TextField(20);
        add(lblApplicantName);
        add(txtApplicantName);

        // Text Area for Output
        txtOutput = new TextArea(10, 50);
        txtOutput.setEditable(false);
        add(txtOutput);

        // Adding action listeners to buttons
        btnPostJob.addActionListener(this);
        btnViewJobs.addActionListener(this);
        btnApplyJob.addActionListener(this);
        btnViewApplications.addActionListener(this);
        btnClear.addActionListener(this);

        // Setting Frame properties
        setSize(600, 400);
        setVisible(true);

        // Close the window on click
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action for Post Job
        if (e.getSource() == btnPostJob) {
            String jobTitle = txtJobTitle.getText().trim();
            if (!jobTitle.isEmpty()) {
                jobPostings.add(jobTitle);
                txtOutput.append("Job posted successfully: " + jobTitle + "\n");
                txtJobTitle.setText("");
            } else {
                txtOutput.append("Error: Job title cannot be empty.\n");
            }
        }

        // Action for View Jobs
        else if (e.getSource() == btnViewJobs) {
            txtOutput.append("\nAvailable Jobs:\n");
            if (jobPostings.isEmpty()) {
                txtOutput.append("No jobs available at the moment.\n");
            } else {
                for (int i = 0; i < jobPostings.size(); i++) {
                    txtOutput.append((i + 1) + ". " + jobPostings.get(i) + "\n");
                }
            }
        }

        // Action for Apply Job
        else if (e.getSource() == btnApplyJob) {
            String jobTitle = txtJobTitle.getText().trim();
            String applicantName = txtApplicantName.getText().trim();

            if (!jobTitle.isEmpty() && !applicantName.isEmpty()) {
                if (jobPostings.contains(jobTitle)) {
                    applications.add(applicantName + " applied for: " + jobTitle);
                    txtOutput.append(applicantName + " has applied for '" + jobTitle + "'.\n");
                    txtJobTitle.setText("");
                    txtApplicantName.setText("");
                } else {
                    txtOutput.append("Error: Job title '" + jobTitle + "' not found.\n");
                }
            } else {
                txtOutput.append("Error: Both job title and applicant name must be provided.\n");
            }
        }

        // Action for View Applications
        else if (e.getSource() == btnViewApplications) {
            txtOutput.append("\nJob Applications:\n");
            if (applications.isEmpty()) {
                txtOutput.append("No applications received yet.\n");
            } else {
                for (String application : applications) {
                    txtOutput.append(application + "\n");
                }
            }
        }

        // Action for Clear Output
        else if (e.getSource() == btnClear) {
            txtOutput.setText("");
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new JobPortalAWT();
    }
}