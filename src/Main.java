import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * GuardianLink - NGO Child Welfare & Sponsorship Management System
 * 
 * This is a demonstration JavaFX application for a university OOP project.
 * Purpose: Show UI development and navigation capabilities.
 * 
 * Features:
 * - Login/Role selection screen
 * - Main dashboard with sidebar navigation
 * - Dashboard, Child Profiles, and Donations pages
 * - Hardcoded sample data (no database integration)
 * 
 * @author Student
 * @version 1.0
 */
public class Main extends Application {

    // Window dimensions
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 800;

    // Color scheme - Dark theme with bold gradients
    private static final String PRIMARY_COLOR = "#00D4FF"; // Cyan
    private static final String SECONDARY_COLOR = "#FF006E"; // Hot Pink
    private static final String BACKGROUND_COLOR = "#0A0E27"; // Very Dark Blue
    private static final String SIDEBAR_COLOR = "#1A1F3A"; // Dark Slate
    private static final String HEADER_COLOR = "#0F1629"; // Deep Navy
    private static final String ACCENT_PURPLE = "#9D4EDD"; // Bold Purple
    private static final String ACCENT_MAGENTA = "#FF006E"; // Bold Magenta

    // Application state
    private Stage primaryStage;
    private String loggedInRole = "Admin"; // Default role for demo

    // Main layout container
    private StackPane mainContainer;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("GuardianLink - NGO Management System");

        // Create main container
        mainContainer = new StackPane();

        // Show login screen first
        showLoginScreen();

        // Create and set the scene
        Scene scene = new Scene(mainContainer, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Creates and displays the login/role selection screen
     * 
     * Purpose: Allow users to select their role and enter credentials
     * Note: Any username/password combination is accepted for demo purposes
     */
    private void showLoginScreen() {
        // Main container for login screen
        VBox loginContainer = new VBox(20);
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #0A0E27, #1A1F3A, #2D0B3D, #1A0033);");

        // Title section
        Label titleLabel = new Label("GuardianLink");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        titleLabel.setTextFill(Color.WHITE);

        Label subtitleLabel = new Label("NGO Child Welfare & Sponsorship Management System");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        subtitleLabel.setTextFill(Color.web("#00D4FF"));

        // Login form container
        VBox formContainer = new VBox(15);
        formContainer.setMaxWidth(400);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(40));
        formContainer.setStyle(
                "-fx-background-color: rgba(30, 35, 60, 0.95); -fx-border-color: #00D4FF; -fx-border-width: 2; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,212,255,0.3), 15, 0, 0, 8);");

        // Role selection
        Label roleLabel = new Label("Select Role:");
        roleLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        roleLabel.setTextFill(Color.web("#00D4FF"));

        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Child", "Caregiver", "Donor", "Support Representative", "Admin");
        roleComboBox.setValue("Admin");
        roleComboBox.setPrefWidth(320);
        roleComboBox.setStyle(
                "-fx-font-size: 14px; -fx-background-color: rgba(26, 31, 58, 0.8); -fx-text-fill: #00D4FF; -fx-border-color: #FF006E; -fx-border-width: 1; -fx-padding: 8;");

        // Username field
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        usernameLabel.setTextFill(Color.web("#00D4FF"));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setPrefWidth(320);
        usernameField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 10; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        // Password field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        passwordLabel.setTextFill(Color.web("#00D4FF"));

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setPrefWidth(320);
        passwordField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 10; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(320);
        loginButton.setPrefHeight(45);
        loginButton.setStyle("-fx-background-color: linear-gradient(to right, #FF006E, #9D4EDD); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;");

        // Login button hover effect
        loginButton.setOnMouseEntered(
                e -> loginButton.setStyle("-fx-background-color: linear-gradient(to right, #FF1744, #BA68C8); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"));
        loginButton.setOnMouseExited(
                e -> loginButton.setStyle("-fx-background-color: linear-gradient(to right, #FF006E, #9D4EDD); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"));

        // Login button action - navigate to dashboard
        loginButton.setOnAction(e -> {
            loggedInRole = roleComboBox.getValue();
            showDashboardScreen();
        });

        // Add all elements to form container
        formContainer.getChildren().addAll(
                roleLabel, roleComboBox,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                loginButton);

        // Add all elements to login container
        loginContainer.getChildren().addAll(titleLabel, subtitleLabel, formContainer);

        // Clear and show login screen
        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(loginContainer);
    }

    /**
     * Creates and displays the main dashboard screen with sidebar navigation
     * 
     * Purpose: Provide main navigation hub for the application
     * Layout: Header bar + Left sidebar + Main content area
     */
    private void showDashboardScreen() {
        // Main layout using BorderPane
        BorderPane dashboardLayout = new BorderPane();
        dashboardLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        // Create header
        HBox header = createHeader();
        dashboardLayout.setTop(header);

        // Create sidebar
        VBox sidebar = createSidebar();
        dashboardLayout.setLeft(sidebar);

        // Create initial content (Dashboard page)
        StackPane contentArea = new StackPane();
        contentArea.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");
        contentArea.getChildren().add(createDashboardPage());
        dashboardLayout.setCenter(contentArea);

        // Update main container
        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(dashboardLayout);
    }

    /**
     * Creates the header bar showing logged-in role
     * 
     * @return HBox containing header elements
     */
    private HBox createHeader() {
        HBox header = new HBox();
        header.setPrefHeight(60);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 30, 0, 30));
        header.setStyle("-fx-background-color: linear-gradient(to right, #0F1629, #1A1F3A); " +
                "-fx-border-color: #00D4FF; " +
                "-fx-border-width: 0 0 2 0;");

        // App title in header
        Label headerTitle = new Label("GuardianLink");
        headerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerTitle.setTextFill(Color.web("#00D4FF"));

        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Logged in role indicator
        Label roleIndicator = new Label("Logged in as: " + loggedInRole);
        roleIndicator.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        roleIndicator.setTextFill(Color.web("#00D4FF"));
        roleIndicator.setStyle("-fx-background-color: rgba(255, 0, 110, 0.15); " +
                "-fx-padding: 10 20; " +
                "-fx-background-radius: 20; " +
                "-fx-border-color: #FF006E; " +
                "-fx-border-width: 1;");

        header.getChildren().addAll(headerTitle, spacer, roleIndicator);
        return header;
    }

    /**
     * Creates the left sidebar with navigation buttons
     * 
     * Navigation buttons:
     * - Dashboard: Shows overview statistics
     * - Child Profiles: Shows list of children
     * - Donations: Shows donation records
     * - Reports: Placeholder for future feature
     * 
     * @return VBox containing sidebar elements
     */
    private VBox createSidebar() {
        VBox sidebar = new VBox(5);
        sidebar.setPrefWidth(220);
        sidebar.setPadding(new Insets(20, 0, 20, 0));
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #1A1F3A, #2D0B3D);");

        // Sidebar buttons
        Button dashboardBtn = createSidebarButton("Dashboard", "📊");
        Button childProfilesBtn = createSidebarButton("Child Profiles", "👶");
        Button donationsBtn = createSidebarButton("Donations", "💰");
        Button reportsBtn = createSidebarButton("Reports", "📋");

        // Button actions - switch content when clicked
        dashboardBtn.setOnAction(e -> switchContent(createDashboardPage()));
        childProfilesBtn.setOnAction(e -> switchContent(createChildProfilesPage()));
        donationsBtn.setOnAction(e -> switchContent(createDonationsPage()));
        reportsBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reports");
            alert.setHeaderText(null);
            alert.setContentText("Reports feature coming soon!");
            alert.showAndWait();
        });

        sidebar.getChildren().addAll(dashboardBtn, childProfilesBtn, donationsBtn, reportsBtn);
        return sidebar;
    }

    /**
     * Helper method to create consistent sidebar buttons
     * 
     * @param text Button label
     * @param icon Emoji icon for the button
     * @return Styled button
     */
    private Button createSidebarButton(String text, String icon) {
        Button button = new Button(icon + "  " + text);
        button.setPrefWidth(220);
        button.setPrefHeight(50);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(0, 0, 0, 30));
        button.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: #00D4FF; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: normal; " +
                "-fx-cursor: hand; " +
                "-fx-border-width: 0;");

        // Hover effects
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to right, rgba(0,212,255,0.2), rgba(255,0,110,0.2)); " +
                        "-fx-text-fill: #FF006E; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand; " +
                        "-fx-border-width: 0;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: #00D4FF; " +
                "-fx-font-size: 14px; " +
                "-fx-font-weight: normal; " +
                "-fx-cursor: hand; " +
                "-fx-border-width: 0;"));

        return button;
    }

    /**
     * Switches the main content area to display a different page
     * 
     * @param newContent The new page to display
     */
    private void switchContent(VBox newContent) {
        BorderPane dashboard = (BorderPane) mainContainer.getChildren().get(0);
        StackPane contentArea = (StackPane) dashboard.getCenter();
        contentArea.getChildren().clear();
        contentArea.getChildren().add(newContent);
    }

    /**
     * Creates the Dashboard page with overview statistics
     * 
     * Purpose: Display key metrics and statistics at a glance
     * Shows: Total children, active sponsors, monthly donations
     * 
     * @return VBox containing dashboard content
     */
    private VBox createDashboardPage() {
        VBox page = new VBox(30);
        page.setPadding(new Insets(40));
        page.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        // Page title
        Label pageTitle = new Label("Dashboard Overview");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        pageTitle.setTextFill(Color.web("#00D4FF"));

        // Statistics cards container
        HBox statsContainer = new HBox(30);
        statsContainer.setAlignment(Pos.CENTER_LEFT);

        // Create statistic cards
        VBox card1 = createStatCard("Total Children", "120", "👶", PRIMARY_COLOR);
        VBox card2 = createStatCard("Active Sponsors", "45", "❤️", SECONDARY_COLOR);
        VBox card3 = createStatCard("Monthly Donations", "৳ 350,000", "💰", "#FF9800");

        statsContainer.getChildren().addAll(card1, card2, card3);

        // Welcome message
        Label welcomeMsg = new Label("Welcome to GuardianLink Management System");
        welcomeMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        welcomeMsg.setTextFill(Color.web("#00D4FF"));
        welcomeMsg.setPadding(new Insets(20, 0, 0, 0));

        Label infoMsg = new Label("This dashboard provides an overview of child welfare and sponsorship activities.");
        infoMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        infoMsg.setTextFill(Color.web("#9D4EDD"));

        page.getChildren().addAll(pageTitle, statsContainer, welcomeMsg, infoMsg);
        return page;
    }

    /**
     * Helper method to create statistic cards
     * 
     * @param title Card title
     * @param value Statistic value
     * @param icon  Emoji icon
     * @param color Card accent color
     * @return VBox containing card content
     */
    private VBox createStatCard(String title, String value, String icon, String color) {
        VBox card = new VBox(10);
        card.setPrefWidth(300);
        card.setPrefHeight(150);
        card.setPadding(new Insets(25));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: linear-gradient(135deg, rgba(0,212,255,0.1), rgba(255,0,110,0.1)); " +
                "-fx-border-color: #00D4FF; " +
                "-fx-border-width: 1.5; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,212,255,0.2), 15, 0, 0, 5);");

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(40));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        valueLabel.setTextFill(Color.web(color));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        titleLabel.setTextFill(Color.web("#9D4EDD"));

        card.getChildren().addAll(iconLabel, valueLabel, titleLabel);
        return card;
    }

    /**
     * Creates the Child Profiles page with sample data
     * 
     * Purpose: Display list of children in the system
     * Shows: Name, Age, Organization, Sponsorship Status
     * Note: Data is hardcoded for demonstration
     * 
     * @return VBox containing child profiles content
     */
    private VBox createChildProfilesPage() {
        VBox page = new VBox(20);
        page.setPadding(new Insets(40));
        page.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        // Page title
        Label pageTitle = new Label("Child Profiles");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        pageTitle.setTextFill(Color.web("#00D4FF"));

        // Create table
        TableView<ChildRecord> table = new TableView<>();
        table.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(26,31,58,0.8), rgba(45,11,61,0.6)); " +
                "-fx-border-color: #00D4FF; " +
                "-fx-border-width: 1; " +
                "-fx-control-inner-background: rgba(26,31,58,0.8); " +
                "-fx-background-radius: 10;");
        table.setPrefHeight(500);

        // Define columns
        TableColumn<ChildRecord, String> nameCol = new TableColumn<>("Child Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(250);

        TableColumn<ChildRecord, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageCol.setPrefWidth(100);

        TableColumn<ChildRecord, String> orgCol = new TableColumn<>("Organization");
        orgCol.setCellValueFactory(new PropertyValueFactory<>("organization"));
        orgCol.setPrefWidth(300);

        TableColumn<ChildRecord, String> statusCol = new TableColumn<>("Sponsorship Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(350);

        table.getColumns().addAll(nameCol, ageCol, orgCol, statusCol);

        // Add sample data
        table.getItems().addAll(
                new ChildRecord("Rahim Ahmed", 8, "Hope Foundation", "Sponsored"),
                new ChildRecord("Fatima Rahman", 10, "Children's Care BD", "Sponsored"),
                new ChildRecord("Karim Hassan", 7, "Hope Foundation", "Awaiting Sponsor"),
                new ChildRecord("Ayesha Begum", 12, "Future Leaders", "Sponsored"),
                new ChildRecord("Ibrahim Ali", 9, "Children's Care BD", "Awaiting Sponsor"),
                new ChildRecord("Nadia Islam", 11, "Hope Foundation", "Sponsored"),
                new ChildRecord("Sohel Khan", 6, "Future Leaders", "Awaiting Sponsor"),
                new ChildRecord("Riya Sultana", 13, "Children's Care BD", "Sponsored"));

        page.getChildren().addAll(pageTitle, table);
        return page;
    }

    /**
     * Creates the Donations page with sample data
     * 
     * Purpose: Display donation records
     * Shows: Donor Name, Child Name, Amount, Date
     * Note: Data is hardcoded for demonstration
     * 
     * @return VBox containing donations content
     */
    private VBox createDonationsPage() {
        VBox page = new VBox(20);
        page.setPadding(new Insets(40));
        page.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        // Page title
        Label pageTitle = new Label("Donation Records");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        pageTitle.setTextFill(Color.web("#00D4FF"));

        // Create table
        TableView<DonationRecord> table = new TableView<>();
        table.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(26,31,58,0.8), rgba(45,11,61,0.6)); " +
                "-fx-border-color: #00D4FF; " +
                "-fx-border-width: 1; " +
                "-fx-control-inner-background: rgba(26,31,58,0.8); " +
                "-fx-background-radius: 10;");
        table.setPrefHeight(500);

        // Define columns
        TableColumn<DonationRecord, String> donorCol = new TableColumn<>("Donor Name");
        donorCol.setCellValueFactory(new PropertyValueFactory<>("donorName"));
        donorCol.setPrefWidth(250);

        TableColumn<DonationRecord, String> childCol = new TableColumn<>("Child Name");
        childCol.setCellValueFactory(new PropertyValueFactory<>("childName"));
        childCol.setPrefWidth(250);

        TableColumn<DonationRecord, String> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountCol.setPrefWidth(200);

        TableColumn<DonationRecord, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setPrefWidth(300);

        table.getColumns().addAll(donorCol, childCol, amountCol, dateCol);

        // Add sample data
        table.getItems().addAll(
                new DonationRecord("Mr. Abdul Karim", "Rahim Ahmed", "৳ 5,000", "2026-01-15"),
                new DonationRecord("Mrs. Salma Khatun", "Fatima Rahman", "৳ 8,000", "2026-01-18"),
                new DonationRecord("Dr. Habib Rahman", "Ayesha Begum", "৳ 10,000", "2026-01-20"),
                new DonationRecord("Ms. Nusrat Jahan", "Nadia Islam", "৳ 6,500", "2026-01-22"),
                new DonationRecord("Mr. Shahid Alam", "Fatima Rahman", "৳ 7,000", "2026-01-24"),
                new DonationRecord("Mrs. Farzana Ahmed", "Rahim Ahmed", "৳ 5,000", "2026-01-25"),
                new DonationRecord("Mr. Rashid Khan", "Ayesha Begum", "৳ 9,000", "2026-01-26"),
                new DonationRecord("Anonymous Donor", "Nadia Islam", "৳ 15,000", "2026-01-27"));

        page.getChildren().addAll(pageTitle, table);
        return page;
    }

    /**
     * Data model class for Child Records
     * Used in TableView for Child Profiles page
     */
    public static class ChildRecord {
        private String name;
        private int age;
        private String organization;
        private String status;

        public ChildRecord(String name, int age, String organization, String status) {
            this.name = name;
            this.age = age;
            this.organization = organization;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getOrganization() {
            return organization;
        }

        public String getStatus() {
            return status;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    /**
     * Data model class for Donation Records
     * Used in TableView for Donations page
     */
    public static class DonationRecord {
        private String donorName;
        private String childName;
        private String amount;
        private String date;

        public DonationRecord(String donorName, String childName, String amount, String date) {
            this.donorName = donorName;
            this.childName = childName;
            this.amount = amount;
            this.date = date;
        }

        public String getDonorName() {
            return donorName;
        }

        public String getChildName() {
            return childName;
        }

        public String getAmount() {
            return amount;
        }

        public String getDate() {
            return date;
        }

        public void setDonorName(String donorName) {
            this.donorName = donorName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    /**
     * Application entry point
     */
    public static void main(String[] args) {
        launch(args);
    }
}
