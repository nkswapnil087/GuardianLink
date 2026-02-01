package com.guardianlink;

import com.guardianlink.controller.AuthController;
import com.guardianlink.controller.OrganizationController;
import com.guardianlink.model.entity.Child;
import com.guardianlink.model.entity.Donation;
import com.guardianlink.model.entity.Organization;
import com.guardianlink.model.user.*;
import com.guardianlink.service.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * GuardianLink Main Application
 * Demonstrates MVC architecture with proper separation of concerns
 */
public class GuardianLinkApp extends Application {

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

    // Controllers and Services
    private AuthController authController;
    private OrganizationController organizationController;
    private ChildService childService;
    private DonationService donationService;
    private OrganizationService organizationService;

    // UI Components
    private Stage primaryStage;
    private StackPane mainContainer;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("GuardianLink - NGO Management System");

        // Initialize controllers and services
        initializeServices();

        // Create main container
        mainContainer = new StackPane();

        // Show login screen
        showLoginScreen();

        // Create and set scene
        Scene scene = new Scene(mainContainer, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true); // Allow resizing and fullscreen
        primaryStage.show();
    }

    private void initializeServices() {
        authController = AuthController.getInstance();
        organizationController = OrganizationController.getInstance();
        childService = ChildService.getInstance();
        donationService = DonationService.getInstance();
        organizationService = OrganizationService.getInstance();
    }

    /**
     * Common Login Screen - All users login here
     */
    private void showLoginScreen() {
        VBox container = new VBox(30);
        container.setAlignment(Pos.CENTER);
        container.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #0A0E27, #1A1F3A, #2D0B3D, #1A0033);");

        // Make container fill the entire window
        container.prefWidthProperty().bind(mainContainer.widthProperty());
        container.prefHeightProperty().bind(mainContainer.heightProperty());

        // Title
        Label titleLabel = new Label("GuardianLink");
        titleLabel.setFont(Font.font("Arial Black", FontWeight.BOLD, 64)); // Increased from 52
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 3);");

        Label subtitleLabel = new Label("NGO Child Welfare & Sponsorship Management System");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 24)); // Increased from 16
        subtitleLabel.setTextFill(Color.web("#00D4FF"));

        // Login form box
        VBox formContainer = new VBox(15);
        formContainer.setMaxWidth(400);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(40));
        formContainer.setStyle(
                "-fx-background-color: rgba(30, 35, 60, 0.95); -fx-border-color: #00D4FF; -fx-border-width: 2; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,212,255,0.3), 15, 0, 0, 8);");

        Label formTitle = new Label("Login to Your Account");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28)); // Increased from 20
        formTitle.setTextFill(Color.web("#00D4FF"));

        // Username field with label
        Label usernameLabel = createFieldLabel("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setPrefWidth(320);
        usernameField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 10; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        // Password field with label
        Label passwordLabel = createFieldLabel("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefWidth(320);
        passwordField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 10; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(320);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(320);
        loginButton.setPrefHeight(45);
        loginButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF006E, #9D4EDD); -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 5;");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter username and password");
                return;
            }

            // Try to login - backend determines user role and redirects accordingly
            if (authController.login(username, password)) {
                showDashboardForUser(authController.getCurrentUser());
            } else {
                messageLabel.setText("Invalid username or password. Please try again or sign up.");
            }
        });

        // Sign up button - for non-admin users
        Button signupButton = new Button("New User? Sign Up Here");
        signupButton.setPrefWidth(320);
        signupButton.setPrefHeight(40);
        signupButton.setStyle("-fx-background-color: white; -fx-text-fill: " + PRIMARY_COLOR
                + "; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 5; -fx-border-color: "
                + PRIMARY_COLOR + "; -fx-border-width: 2; -fx-border-radius: 5;");
        signupButton.setOnAction(e -> showSignupRoleSelection());

        formContainer.getChildren().addAll(
                formTitle,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                messageLabel,
                loginButton,
                signupButton);

        container.getChildren().addAll(titleLabel, subtitleLabel, formContainer);

        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(container);
    }

    /**
     * Sign up role selection - Only non-admin roles can sign up
     */
    private void showSignupRoleSelection() {
        VBox container = new VBox(30);
        container.setAlignment(Pos.CENTER);
        container.setStyle(
                "-fx-background-color: linear-gradient(to bottom, " + SIDEBAR_COLOR + ", " + PRIMARY_COLOR + ");");

        // Make container fill the entire window
        container.prefWidthProperty().bind(mainContainer.widthProperty());
        container.prefHeightProperty().bind(mainContainer.heightProperty());

        // Title
        Label titleLabel = new Label("Create an Account");
        titleLabel.setFont(Font.font("Arial Black", FontWeight.BOLD, 52)); // Increased from 36
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 3);");

        Label subtitleLabel = new Label("Select your role to continue");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 22)); // Increased from 16
        subtitleLabel.setTextFill(Color.web("#E3F2FD"));

        // Role selection box - Only non-admin roles
        VBox roleBox = new VBox(20);
        roleBox.setMaxWidth(500);
        roleBox.setAlignment(Pos.CENTER);
        roleBox.setPadding(new Insets(40));
        roleBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 5);");

        Label roleLabel = new Label("I am a:");
        roleLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 28)); // Increased from 20
        roleLabel.setTextFill(Color.web("#333333"));

        // Role buttons - NO ADMIN OPTIONS
        HBox roleRow = new HBox(15);
        roleRow.setAlignment(Pos.CENTER);

        Button donorBtn = createRoleButton("DONOR", "#E91E63");
        Button caregiverBtn = createRoleButton("CAREGIVER", "#9B59B6");

        roleRow.getChildren().addAll(donorBtn, caregiverBtn);

        // Button actions
        donorBtn.setOnAction(e -> showSignupPage(UserRole.DONOR));
        caregiverBtn.setOnAction(e -> showSignupPage(UserRole.CAREGIVER));

        // Info message about admin accounts
        Label infoLabel = new Label("Note: Admin accounts are created by system administrators only.");
        infoLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        infoLabel.setTextFill(Color.web("#888888"));
        infoLabel.setWrapText(true);
        infoLabel.setMaxWidth(400);
        infoLabel.setAlignment(Pos.CENTER);
        infoLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Back button
        Button backButton = new Button("← Back to Login");
        backButton.setPrefWidth(320);
        backButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #666; -fx-font-size: 12px; -fx-cursor: hand;");
        backButton.setOnAction(e -> showLoginScreen());

        roleBox.getChildren().addAll(roleLabel, roleRow, infoLabel, backButton);
        container.getChildren().addAll(titleLabel, subtitleLabel, roleBox);

        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(container);
    }

    /**
     * Create styled role button with gradient and shadow
     */
    private Button createRoleButton(String text, String color) {
        Button btn = new Button(text);
        btn.setPrefSize(150, 100);
        btn.setStyle("-fx-background-color: linear-gradient(to bottom, " + color + ", derive(" + color + ", -20%)); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 11px; " +
                "-fx-font-weight: 900; " +
                "-fx-font-family: 'Arial Black', sans-serif; " +
                "-fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 4); " +
                "-fx-cursor: hand;");
        btn.setAlignment(Pos.CENTER);
        btn.setWrapText(true);

        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: linear-gradient(to bottom, derive(" + color + ", 20%), " + color + "); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 12px; " +
                        "-fx-font-weight: 900; " +
                        "-fx-font-family: 'Arial Black', sans-serif; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 12, 0, 0, 6); " +
                        "-fx-cursor: hand; " +
                        "-fx-scale-x: 1.05; " +
                        "-fx-scale-y: 1.05;"));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: linear-gradient(to bottom, " + color + ", derive(" + color + ", -20%)); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 11px; " +
                        "-fx-font-weight: 900; " +
                        "-fx-font-family: 'Arial Black', sans-serif; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 4); " +
                        "-fx-cursor: hand; " +
                        "-fx-scale-x: 1.0; " +
                        "-fx-scale-y: 1.0;"));
        return btn;
    }

    /**
     * Helper method to create styled field labels
     */
    private Label createFieldLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        label.setTextFill(Color.web("#00D4FF"));
        label.setPadding(new Insets(8, 0, 5, 0));
        label.setStyle("-fx-text-fill: #00D4FF; -fx-font-weight: bold;");
        return label;
    }

    /**
     * Sign up page with role-specific fields
     */
    private void showSignupPage(UserRole role) {
        VBox container = new VBox(25);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(40));
        container.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #0A0E27, #1A1F3A, #2D0B3D, #1A0033);");

        // Make container fill the entire window
        container.prefWidthProperty().bind(mainContainer.widthProperty());
        container.prefHeightProperty().bind(mainContainer.heightProperty());

        // Page title at top
        Label pageTitle = new Label("CREATE YOUR ACCOUNT");
        pageTitle.setFont(Font.font("Arial Black", FontWeight.BOLD, 32));
        pageTitle.setTextFill(Color.WHITE);
        pageTitle.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 3);");

        Label pageSubtitle = new Label(role.getDisplayName());
        pageSubtitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        pageSubtitle.setTextFill(Color.web("#00D4FF"));

        // Form container (The white box)
        VBox formContainer = new VBox(12);
        formContainer.setMaxWidth(450);
        formContainer.setAlignment(Pos.TOP_CENTER);
        formContainer.setPadding(new Insets(40));
        formContainer.setStyle(
                "-fx-background-color: rgba(30, 35, 60, 0.95); -fx-border-color: #00D4FF; -fx-border-width: 2; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,212,255,0.3), 15, 0, 0, 8);");

        // Common fields with better styling
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Enter your full name");
        fullNameField.setPrefWidth(370);
        fullNameField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 12; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Choose a username");
        usernameField.setPrefWidth(370);
        usernameField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 12; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Create a password");
        passwordField.setPrefWidth(370);
        passwordField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 12; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Re-enter your password");
        confirmPasswordField.setPrefWidth(370);
        confirmPasswordField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 12; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        TextField emailField = new TextField();
        emailField.setPromptText("Your email address");
        emailField.setPrefWidth(370);
        emailField.setStyle(
                "-fx-font-size: 14px; -fx-padding: 12; -fx-background-color: rgba(20, 25, 45, 0.9); -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #999999; -fx-border-color: #9D4EDD; -fx-border-width: 1; -fx-background-radius: 5;");

        // Add common fields to form
        formContainer.getChildren().addAll(
                createFieldLabel("Full Name:"), fullNameField,
                createFieldLabel("Username:"), usernameField,
                createFieldLabel("Email Address:"), emailField,
                createFieldLabel("Password:"), passwordField,
                createFieldLabel("Confirm Password:"), confirmPasswordField);

        // Role-specific fields
        TextField orgField = null;
        TextField specializationField = null;
        TextField certificationField = null;
        ComboBox<String> paymentMethodField = null;

        switch (role) {
            case CAREGIVER:
                orgField = new TextField();
                orgField.setPromptText("Enter organization ID");
                orgField.setPrefWidth(370);
                orgField.setStyle(
                        "-fx-font-size: 14px; -fx-padding: 12; -fx-background-radius: 8; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-border-width: 1;");
                specializationField = new TextField();
                specializationField.setPromptText("e.g., Child Psychology, Healthcare");
                specializationField.setPrefWidth(370);
                specializationField.setStyle(
                        "-fx-font-size: 14px; -fx-padding: 12; -fx-background-radius: 8; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-border-width: 1;");
                formContainer.getChildren().addAll(
                        createFieldLabel("Organization ID:"), orgField,
                        createFieldLabel("Specialization:"), specializationField);
                break;

            case DONOR:
                paymentMethodField = new ComboBox<>();
                paymentMethodField.getItems().addAll(
                        "Cash",
                        "Visa",
                        "Mastercard",
                        "Bank Transfer",
                        "Mobile Banking",
                        "PayPal",
                        "Check",
                        "Wire Transfer");
                paymentMethodField.setPromptText("Select a payment method");
                paymentMethodField.setPrefWidth(370);
                paymentMethodField.setStyle(
                        "-fx-font-size: 14px; -fx-padding: 8; -fx-background-radius: 8; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-border-width: 1;");
                formContainer.getChildren().addAll(createFieldLabel("Preferred Payment Method:"), paymentMethodField);
                break;

            case AUDITOR:
                certificationField = new TextField();
                certificationField.setPromptText("Enter your certification number");
                certificationField.setPrefWidth(370);
                certificationField.setStyle(
                        "-fx-font-size: 14px; -fx-padding: 12; -fx-background-radius: 8; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-border-width: 1;");
                formContainer.getChildren().addAll(createFieldLabel("Certification Number:"), certificationField);
                break;
        }

        Label messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(370);
        messageLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));

        Region spacer = new Region();
        spacer.setPrefHeight(10);

        Button signupButton = new Button("CREATE ACCOUNT");
        signupButton.setPrefWidth(370);
        signupButton.setPrefHeight(50);
        signupButton.setStyle("-fx-background-color: linear-gradient(to right, #FF006E, #9D4EDD); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 15px; " +
                "-fx-font-weight: 900; " +
                "-fx-font-family: 'Arial Black', sans-serif; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,212,255,0.2), 10, 0, 0, 5);");

        // Final references for lambda
        final TextField finalOrgField = orgField;
        final TextField finalSpecField = specializationField;
        final TextField finalCertField = certificationField;
        final ComboBox<String> finalPaymentField = paymentMethodField;

        signupButton.setOnAction(e -> {
            String fullName = fullNameField.getText();
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            // Validation
            if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Please fill in all required fields");
                return;
            }

            if (!password.equals(confirmPassword)) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Passwords do not match");
                return;
            }

            // Generate user ID
            UserService userService = UserService.getInstance();
            String userId = userService.generateNextUserId();

            // Create user based on role (only non-admin roles can sign up)
            User newUser = null;
            switch (role) {
                case CAREGIVER:
                    String org = finalOrgField != null ? finalOrgField.getText() : "ORG001";
                    String spec = finalSpecField != null ? finalSpecField.getText() : "General Care";
                    newUser = new Caregiver(userId, username, password, fullName, email, org, spec);
                    break;
                case DONOR:
                    newUser = new Donor(userId, username, password, fullName, email);
                    if (finalPaymentField != null && finalPaymentField.getValue() != null) {
                        ((Donor) newUser).setPreferredPaymentMethod(finalPaymentField.getValue());
                    }
                    break;
                case AUDITOR:
                    String cert = finalCertField != null ? finalCertField.getText()
                            : "AUD-" + System.currentTimeMillis();
                    newUser = new Auditor(userId, username, password, fullName, email, cert);
                    break;
                default:
                    // Admin roles cannot sign up through this form
                    messageLabel.setTextFill(Color.RED);
                    messageLabel.setText("Admin accounts cannot be created through sign up.");
                    return;
            }

            // Register user
            if (newUser != null) {
                userService.registerUser(newUser);
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Account created successfully! Redirecting to login...");

                // Delay then show login
                javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                        javafx.util.Duration.seconds(1.5));
                pause.setOnFinished(event -> showLoginScreen());
                pause.play();
            }
        });

        Button backButton = new Button("← Back to Sign Up Options");
        backButton.setPrefWidth(370);
        backButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #999; -fx-font-size: 12px; -fx-font-weight: bold; -fx-cursor: hand; -fx-underline: false;");
        backButton.setOnMouseEntered(
                e -> backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY_COLOR
                        + "; -fx-font-size: 12px; -fx-font-weight: bold; -fx-cursor: hand; -fx-underline: true;"));
        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #999; -fx-font-size: 12px; -fx-font-weight: bold; -fx-cursor: hand; -fx-underline: false;"));
        backButton.setOnAction(e -> showSignupRoleSelection());

        formContainer.getChildren().addAll(messageLabel, spacer, signupButton, backButton);

        // StackPane to center the form within the ScrollPane
        StackPane formWrapper = new StackPane(formContainer);
        formWrapper.setAlignment(Pos.CENTER);
        formWrapper.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(formWrapper);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(500);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        container.getChildren().addAll(pageTitle, pageSubtitle, scrollPane);

        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(container);
    }

    /**
     * Show appropriate dashboard based on user role
     */
    private void showDashboardForUser(User user) {
        if (user instanceof SystemAdmin || user instanceof OrganizationAdmin) {
            showAdminDashboard(user);
        } else if (user instanceof Donor) {
            showDonorDashboard((Donor) user);
        } else if (user instanceof Caregiver) {
            showCaregiverDashboard((Caregiver) user);
        } else {
            showBasicDashboard(user);
        }
    }

    /**
     * ADMIN DASHBOARD - Fully Functional
     * Can add/remove/edit children
     */
    private void showAdminDashboard(User admin) {
        BorderPane dashboardLayout = new BorderPane();
        dashboardLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        // Make dashboard fill the entire window
        dashboardLayout.prefWidthProperty().bind(mainContainer.widthProperty());
        dashboardLayout.prefHeightProperty().bind(mainContainer.heightProperty());

        // Header
        HBox header = createHeader(admin);
        dashboardLayout.setTop(header);

        // Sidebar
        VBox sidebar = createAdminSidebar();
        dashboardLayout.setLeft(sidebar);

        // Initial content
        StackPane contentArea = new StackPane();
        contentArea.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");
        contentArea.getChildren().add(createAdminDashboardPage());
        dashboardLayout.setCenter(contentArea);

        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(dashboardLayout);
    }

    private VBox createAdminSidebar() {
        VBox sidebar = new VBox(8);
        sidebar.setPrefWidth(240);
        sidebar.setPadding(new Insets(25, 0, 25, 0));
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #1A1F3A, #2D0B3D);");

        Button dashboardBtn = createSidebarButton("DASHBOARD", "#42A5F5");
        Button childrenBtn = createSidebarButton("MANAGE CHILDREN", "#66BB6A");
        Button donationsBtn = createSidebarButton("VIEW DONATIONS", "#FFCA28");
        Button logoutBtn = createSidebarButton("LOGOUT", "#EF5350");

        dashboardBtn.setOnAction(e -> switchAdminContent(createAdminDashboardPage()));
        childrenBtn.setOnAction(e -> switchAdminContent(createManageChildrenPage()));
        donationsBtn.setOnAction(e -> switchAdminContent(createViewDonationsPage()));
        logoutBtn.setOnAction(e -> {
            authController.logout();
            showLoginScreen();
        });

        sidebar.getChildren().addAll(dashboardBtn, childrenBtn, donationsBtn);

        // Only System Admin can manage organizations
        User currentUser = authController.getCurrentUser();
        if (currentUser instanceof SystemAdmin) {
            Button organizationBtn = createSidebarButton("MANAGE ORGANIZATIONS", "#AB47BC");
            organizationBtn.setOnAction(e -> switchAdminContent(createManageOrganizationsPage()));
            sidebar.getChildren().add(organizationBtn);
        }

        sidebar.getChildren().add(logoutBtn);
        return sidebar;
    }

    private VBox createAdminDashboardPage() {
        VBox page = new VBox(30);
        page.setPadding(new Insets(40));
        page.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        Label pageTitle = new Label("Admin Dashboard");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        pageTitle.setTextFill(Color.web("#00D4FF"));

        // Statistics
        HBox statsContainer = new HBox(25);
        statsContainer.setAlignment(Pos.CENTER);
        int totalChildren = childService.getTotalChildrenCount();
        int sponsored = childService.getSponsoredChildrenCount();
        double totalDonations = donationService.getTotalDonationAmount();

        VBox card1 = createStatCard("Total Children", String.valueOf(totalChildren), "#2196F3");
        VBox card2 = createStatCard("Sponsored Children", String.valueOf(sponsored), "#4CAF50");
        VBox card3 = createStatCard("Total Donations", "৳" + String.format("%.0f", totalDonations), "#FF9800");

        statsContainer.getChildren().addAll(card1, card2, card3);

        page.getChildren().addAll(pageTitle, statsContainer);
        return page;
    }

    /**
     * MANAGE CHILDREN PAGE - Add/Edit/Delete Children
     * SystemAdmin can manage all children, OrganizationAdmin can manage only their
     * organization's children
     */
    private VBox createManageChildrenPage() {
        VBox page = new VBox(20);
        page.setPadding(new Insets(40));

        Label pageTitle = new Label("Manage Children");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 36)); // Increased from 28
        pageTitle.setTextFill(Color.WHITE);
        pageTitle.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 3);");

        // Add Child Button
        Button addChildBtn = new Button("+ Add New Child");
        addChildBtn.setStyle("-fx-background-color: " + SECONDARY_COLOR
                + "; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        addChildBtn.setOnAction(e -> showAddChildDialog());

        // Children Table
        TableView<Child> table = new TableView<>();
        table.setStyle("-fx-background-color: white;");
        table.setPrefHeight(500);

        TableColumn<Child, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("childId"));
        idCol.setPrefWidth(80);

        TableColumn<Child, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(180);

        TableColumn<Child, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageCol.setPrefWidth(60);

        TableColumn<Child, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        genderCol.setPrefWidth(80);

        TableColumn<Child, String> orgCol = new TableColumn<>("Organization");
        orgCol.setCellValueFactory(new PropertyValueFactory<>("organizationId"));
        orgCol.setPrefWidth(120);

        TableColumn<Child, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("sponsorshipStatus"));
        statusCol.setPrefWidth(150);

        TableColumn<Child, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setPrefWidth(200);
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox box = new HBox(10, editBtn, deleteBtn);

            {
                editBtn.setStyle("-fx-background-color: " + PRIMARY_COLOR + "; -fx-text-fill: white;");
                deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

                editBtn.setOnAction(e -> {
                    Child child = getTableView().getItems().get(getIndex());
                    showEditChildDialog(child, table);
                });

                deleteBtn.setOnAction(e -> {
                    Child child = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle("Confirm Delete");
                    confirm.setHeaderText("Delete " + child.getName() + "?");
                    confirm.setContentText("This action cannot be undone.");

                    if (confirm.showAndWait().get() == ButtonType.OK) {
                        childService.deleteChild(child.getChildId());
                        table.getItems().remove(child);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });

        table.getColumns().addAll(idCol, nameCol, ageCol, genderCol, orgCol, statusCol, actionCol);

        // Load children based on user role
        User currentUser = authController.getCurrentUser();
        if (currentUser instanceof SystemAdmin) {
            // System Admin sees all children
            table.getItems().addAll(childService.getAllChildren());
        } else if (currentUser instanceof OrganizationAdmin) {
            // Organization Admin sees only their organization's children
            OrganizationAdmin orgAdmin = (OrganizationAdmin) currentUser;
            table.getItems().addAll(childService.getChildrenByOrganization(orgAdmin.getOrganizationId()));
        }

        page.getChildren().addAll(pageTitle, addChildBtn, table);
        return page;
    }

    /**
     * Dialog to add new child
     */
    private void showAddChildDialog() {
        Dialog<Child> dialog = new Dialog<>();
        dialog.setTitle("Add New Child");
        dialog.setHeaderText("Enter child information");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField();
        TextField ageField = new TextField();
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setValue("Male");

        ComboBox<String> orgBox = new ComboBox<>();
        List<Organization> orgs = organizationService.getAllOrganizations();
        for (Organization org : orgs) {
            orgBox.getItems().add(org.getOrganizationId() + " - " + org.getName());
        }
        if (!orgs.isEmpty()) {
            orgBox.setValue(orgs.get(0).getOrganizationId() + " - " + orgs.get(0).getName());
        }

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Age:"), 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(new Label("Gender:"), 0, 2);
        grid.add(genderBox, 1, 2);
        grid.add(new Label("Organization:"), 0, 3);
        grid.add(orgBox, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String gender = genderBox.getValue();
                    String orgId = orgBox.getValue().split(" - ")[0];
                    String childId = childService.generateNextChildId();
                    String date = LocalDate.now().toString();

                    Child child = new Child(childId, name, age, gender, orgId, date);
                    childService.addChild(child);
                    return child;
                } catch (Exception e) {
                    showAlert("Error", "Invalid input: " + e.getMessage());
                }
            }
            return null;
        });

        Optional<Child> result = dialog.showAndWait();
        if (result.isPresent()) {
            showAlert("Success", "Child added successfully!");
            switchAdminContent(createManageChildrenPage());
        }
    }

    /**
     * Dialog to edit child
     */
    private void showEditChildDialog(Child child, TableView<Child> table) {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Edit Child");
        dialog.setHeaderText("Edit information for " + child.getName());

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField(child.getName());
        TextField ageField = new TextField(String.valueOf(child.getAge()));
        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("Sponsored", "Awaiting Sponsor");
        statusBox.setValue(child.getSponsorshipStatus());

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Age:"), 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(new Label("Status:"), 0, 2);
        grid.add(statusBox, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                child.setName(nameField.getText());
                child.setAge(Integer.parseInt(ageField.getText()));
                child.setSponsorshipStatus(statusBox.getValue());
                childService.updateChild(child);
                return true;
            }
            return false;
        });

        Optional<Boolean> result = dialog.showAndWait();
        if (result.isPresent() && result.get()) {
            table.refresh();
            showAlert("Success", "Child updated successfully!");
        }
    }

    /**
     * View all donations
     */
    private VBox createViewDonationsPage() {
        VBox page = new VBox(20);
        page.setPadding(new Insets(40));

        Label pageTitle = new Label("Donation Records");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 36)); // Increased from 28
        pageTitle.setTextFill(Color.WHITE);
        pageTitle.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 3);");

        TableView<DonationDisplay> table = new TableView<>();
        table.setStyle("-fx-background-color: white;");
        table.setPrefHeight(500);

        TableColumn<DonationDisplay, String> donorCol = new TableColumn<>("Donor");
        donorCol.setCellValueFactory(new PropertyValueFactory<>("donorName"));
        donorCol.setPrefWidth(200);

        TableColumn<DonationDisplay, String> childCol = new TableColumn<>("Child");
        childCol.setCellValueFactory(new PropertyValueFactory<>("childName"));
        childCol.setPrefWidth(200);

        TableColumn<DonationDisplay, String> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountCol.setPrefWidth(150);

        TableColumn<DonationDisplay, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setPrefWidth(200);

        TableColumn<DonationDisplay, String> methodCol = new TableColumn<>("Payment Method");
        methodCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        methodCol.setPrefWidth(200);

        table.getColumns().addAll(donorCol, childCol, amountCol, dateCol, methodCol);

        // Populate with data
        List<Donation> donations = donationService.getAllDonations();
        for (Donation d : donations) {
            Child child = childService.getChildById(d.getChildId());
            String childName = child != null ? child.getName() : "Unknown";
            table.getItems().add(
                    new DonationDisplay("Donor", childName, "৳ " + d.getAmount(), d.getDate(), d.getPaymentMethod()));
        }

        page.getChildren().addAll(pageTitle, table);
        return page;
    }

    /**
     * MANAGE ORGANIZATIONS PAGE - Add/Edit/Delete Organizations (System Admin Only)
     */
    private VBox createManageOrganizationsPage() {
        VBox page = new VBox(20);
        page.setPadding(new Insets(40));

        Label pageTitle = new Label("Manage Organizations");
        pageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        pageTitle.setTextFill(Color.WHITE);
        pageTitle.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 3);");

        // Add Organization Button
        Button addOrgBtn = new Button("+ Add New Organization");
        addOrgBtn.setStyle("-fx-background-color: #AB47BC"
                + "; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        addOrgBtn.setOnAction(e -> showAddOrganizationDialog());

        // Organizations Table
        TableView<Organization> table = new TableView<>();
        table.setStyle("-fx-background-color: white;");
        table.setPrefHeight(500);

        TableColumn<Organization, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("organizationId"));
        idCol.setPrefWidth(100);

        TableColumn<Organization, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);

        TableColumn<Organization, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(220);

        TableColumn<Organization, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(130);

        TableColumn<Organization, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(150);

        TableColumn<Organization, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setPrefWidth(220);
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button assignBtn = new Button("Assign Admin");
            private final Button deleteBtn = new Button("Delete");
            private final HBox box = new HBox(10, assignBtn, deleteBtn);

            {
                assignBtn.setStyle("-fx-background-color: #0288D1; -fx-text-fill: white;");
                deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

                assignBtn.setOnAction(e -> {
                    Organization org = getTableView().getItems().get(getIndex());
                    showAssignOrgAdminDialog(org);
                });

                deleteBtn.setOnAction(e -> {
                    Organization org = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle("Confirm Delete");
                    confirm.setHeaderText("Delete " + org.getName() + "?");
                    confirm.setContentText("This action cannot be undone.");
                    Optional<ButtonType> result = confirm.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        organizationController.deleteOrganization(authController.getCurrentUser(),
                                org.getOrganizationId());
                        table.refresh();
                        loadOrganizations(table);
                        showAlert("Success", "Organization deleted successfully!");
                    }
                });

                this.setStyle("-fx-alignment: CENTER;");
                this.setGraphic(box);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });

        table.getColumns().addAll(idCol, nameCol, addressCol, phoneCol, emailCol, actionCol);

        // Load organizations
        loadOrganizations(table);

        page.getChildren().addAll(pageTitle, addOrgBtn, table);
        return page;
    }

    private void loadOrganizations(TableView<Organization> table) {
        table.getItems().clear();
        List<Organization> organizations = organizationController.getAllOrganizations();
        table.getItems().addAll(organizations);
    }

    private void showAddOrganizationDialog() {
        Dialog<Organization> dialog = new Dialog<>();
        dialog.setTitle("Add New Organization");
        dialog.setHeaderText("Create a New Organization");

        ButtonType saveButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Organization Name");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressField, 1, 1);
        grid.add(new Label("Phone:"), 0, 2);
        grid.add(phoneField, 1, 2);
        grid.add(new Label("Email:"), 0, 3);
        grid.add(emailField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                        phoneField.getText().isEmpty() || emailField.getText().isEmpty()) {
                    showAlert("Validation Error", "All fields are required!");
                    return null;
                }
                return organizationController.addOrganization(
                        authController.getCurrentUser(),
                        nameField.getText(),
                        addressField.getText(),
                        phoneField.getText(),
                        emailField.getText());
            }
            return null;
        });

        Optional<Organization> result = dialog.showAndWait();
        if (result.isPresent()) {
            showAlert("Success", "Organization created successfully!");
            // Refresh the table using switchAdminContent
            switchAdminContent(createManageOrganizationsPage());
        }
    }

    /**
     * Dialog to assign organization admin to an organization
     */
    private void showAssignOrgAdminDialog(Organization organization) {
        Dialog<OrganizationAdmin> dialog = new Dialog<>();
        dialog.setTitle("Assign Organization Admin");
        dialog.setHeaderText("Create and assign a new Organization Admin to: " + organization.getName());

        ButtonType assignButtonType = new ButtonType("Assign", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(assignButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Full Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("Full Name:"), 0, 2);
        grid.add(fullNameField, 1, 2);
        grid.add(new Label("Email:"), 0, 3);
        grid.add(emailField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == assignButtonType) {
                if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                        fullNameField.getText().isEmpty() || emailField.getText().isEmpty()) {
                    showAlert("Validation Error", "All fields are required!");
                    return null;
                }
                String userId = "USR" + System.currentTimeMillis();
                return organizationController.assignOrgAdmin(
                        authController.getCurrentUser(),
                        userId,
                        usernameField.getText(),
                        passwordField.getText(),
                        fullNameField.getText(),
                        emailField.getText(),
                        organization.getOrganizationId());
            }
            return null;
        });

        Optional<OrganizationAdmin> result = dialog.showAndWait();
        if (result.isPresent()) {
            showAlert("Success", "Organization Admin assigned successfully!\n" +
                    "Admin: " + result.get().getFullName() + "\n" +
                    "Organization: " + organization.getName());
        }
    }

    /**
     * DONOR DASHBOARD - Basic View
     */
    private void showDonorDashboard(Donor donor) {
        BorderPane dashboardLayout = new BorderPane();
        dashboardLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        // Make dashboard fill the entire window
        dashboardLayout.prefWidthProperty().bind(mainContainer.widthProperty());
        dashboardLayout.prefHeightProperty().bind(mainContainer.heightProperty());

        dashboardLayout.setTop(createHeader(donor));
        dashboardLayout.setLeft(createBasicSidebar(donor));

        VBox content = new VBox(30);
        content.setPadding(new Insets(40));
        content.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        Label title = new Label("Donor Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36)); // Increased from 28
        title.setTextFill(Color.web("#00D4FF"));

        Label welcomeMsg = new Label("Welcome, " + donor.getFullName() + "!");
        welcomeMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 24)); // Increased from 18
        welcomeMsg.setTextFill(Color.web("#9D4EDD"));

        Label statsMsg = new Label("Total Donated: ৳ " + donor.getTotalDonated());
        statsMsg.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 22)); // Increased from 16

        content.getChildren().addAll(title, welcomeMsg, statsMsg);

        dashboardLayout.setCenter(content);

        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(dashboardLayout);
    }

    /**
     * CAREGIVER DASHBOARD - Basic View
     */
    private void showCaregiverDashboard(Caregiver caregiver) {
        BorderPane dashboardLayout = new BorderPane();
        dashboardLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        // Make dashboard fill the entire window
        dashboardLayout.prefWidthProperty().bind(mainContainer.widthProperty());
        dashboardLayout.prefHeightProperty().bind(mainContainer.heightProperty());

        dashboardLayout.setTop(createHeader(caregiver));
        dashboardLayout.setLeft(createBasicSidebar(caregiver));

        VBox content = new VBox(30);
        content.setPadding(new Insets(40));
        content.setStyle("-fx-background-color: linear-gradient(to bottom, #0A0E27, #1A1F3A);");

        Label title = new Label("Caregiver Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36)); // Increased from 28
        title.setTextFill(Color.web("#00D4FF"));

        Label welcomeMsg = new Label("Welcome, " + caregiver.getFullName() + "!");
        welcomeMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 24)); // Increased from 18
        welcomeMsg.setTextFill(Color.web("#9D4EDD"));

        Label orgMsg = new Label(
                "Organization: " + organizationService.getOrganizationName(caregiver.getOrganizationId()));
        orgMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 22)); // Increased from 16
        orgMsg.setTextFill(Color.web("#9D4EDD"));

        content.getChildren().addAll(title, welcomeMsg, orgMsg);

        dashboardLayout.setCenter(content);

        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(dashboardLayout);
    }

    /**
     * BASIC DASHBOARD for other user types
     */
    private void showBasicDashboard(User user) {
        BorderPane dashboardLayout = new BorderPane();
        dashboardLayout.setStyle("-fx-background-color: " + BACKGROUND_COLOR + ";");

        // Make dashboard fill the entire window
        dashboardLayout.prefWidthProperty().bind(mainContainer.widthProperty());
        dashboardLayout.prefHeightProperty().bind(mainContainer.heightProperty());

        dashboardLayout.setTop(createHeader(user));
        dashboardLayout.setLeft(createBasicSidebar(user));

        VBox content = new VBox(30);
        content.setPadding(new Insets(40));

        Label title = new Label(user.getDashboardTitle());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36)); // Increased from 28

        Label welcomeMsg = new Label("Welcome, " + user.getFullName() + "!");
        welcomeMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 24)); // Increased from 18

        content.getChildren().addAll(title, welcomeMsg);

        dashboardLayout.setCenter(content);

        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(dashboardLayout);
    }

    // Helper methods
    private HBox createHeader(User user) {
        HBox header = new HBox();
        header.setPrefHeight(60);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 30, 0, 30));
        header.setStyle(
                "-fx-background-color: linear-gradient(to right, #0F1629, #1A1F3A); -fx-border-color: #00D4FF; -fx-border-width: 0 0 2 0;");

        Label headerTitle = new Label("GuardianLink");
        headerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 28)); // Increased from 20
        headerTitle.setTextFill(Color.web("#00D4FF"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label roleIndicator = new Label("Logged in as: " + user.getRole().getDisplayName());
        roleIndicator.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20)); // Increased from 14
        roleIndicator.setTextFill(Color.web("#00D4FF"));
        roleIndicator.setStyle(
                "-fx-background-color: rgba(255, 0, 110, 0.15); -fx-padding: 10 20; -fx-background-radius: 20; -fx-border-color: #FF006E; -fx-border-width: 1;");

        header.getChildren().addAll(headerTitle, spacer, roleIndicator);
        return header;
    }

    private VBox createBasicSidebar(User user) {
        VBox sidebar = new VBox(8);
        sidebar.setPrefWidth(240);
        sidebar.setPadding(new Insets(25, 0, 25, 0));
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #1A1F3A, #2D0B3D);");

        Button dashboardBtn = createSidebarButton("DASHBOARD", "#9575CD");
        Button logoutBtn = createSidebarButton("LOGOUT", "#EF5350");

        logoutBtn.setOnAction(e -> {
            authController.logout();
            showLoginScreen();
        });

        sidebar.getChildren().addAll(dashboardBtn, logoutBtn);
        return sidebar;
    }

    private void switchAdminContent(VBox newContent) {
        BorderPane dashboard = (BorderPane) mainContainer.getChildren().get(0);
        dashboard.setCenter(newContent);
    }

    private VBox createStatCard(String title, String value, String color) {
        VBox card = new VBox(15);
        card.setPrefWidth(320);
        card.setPrefHeight(170);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER_LEFT);

        // Gradient background with accent color
        card.setStyle("-fx-background-color: linear-gradient(135deg, rgba(0,212,255,0.1), rgba(255,0,110,0.1)); " +
                "-fx-border-color: #00D4FF; " +
                "-fx-border-width: 1.5; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,212,255,0.2), 15, 0, 0, 5);");

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial Black", FontWeight.BOLD, 40));
        valueLabel.setTextFill(Color.web(color));
        valueLabel.setStyle("-fx-effect: dropshadow(gaussian, " + color + "40, 5, 0, 0, 2);");

        Label titleLabel = new Label(title.toUpperCase());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        titleLabel.setTextFill(Color.web("#9D4EDD"));

        // Color bar accent
        Region colorBar = new Region();
        colorBar.setPrefHeight(4);
        colorBar.setMaxWidth(80);
        colorBar.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 2;");

        card.getChildren().addAll(valueLabel, titleLabel, colorBar);
        return card;
    }

    private Button createSidebarButton(String text, String accentColor) {
        Button button = new Button(text);
        button.setPrefWidth(240);
        button.setPrefHeight(55);
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(0, 15, 0, 15));
        button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.05); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 11px; " +
                "-fx-font-weight: 900; " +
                "-fx-font-family: 'Arial Black'; " +
                "-fx-background-radius: 10; " +
                "-fx-cursor: hand; " +
                "-fx-border-color: " + accentColor + "55; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 10;");
        button.setWrapText(true);

        // Hover effects with color
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: " + accentColor + "66; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 11px; " +
                "-fx-font-weight: 900; " +
                "-fx-font-family: 'Arial Black'; " +
                "-fx-background-radius: 10; " +
                "-fx-cursor: hand; " +
                "-fx-border-color: " + accentColor + "; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, " + accentColor + "88, 10, 0, 0, 0);"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.05); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 11px; " +
                "-fx-font-weight: 900; " +
                "-fx-font-family: 'Arial Black'; " +
                "-fx-background-radius: 10; " +
                "-fx-cursor: hand; " +
                "-fx-border-color: " + accentColor + "55; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 10;"));

        return button;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper class for donation display
    public static class DonationDisplay {
        private String donorName;
        private String childName;
        private String amount;
        private String date;
        private String paymentMethod;

        public DonationDisplay(String donorName, String childName, String amount, String date, String paymentMethod) {
            this.donorName = donorName;
            this.childName = childName;
            this.amount = amount;
            this.date = date;
            this.paymentMethod = paymentMethod;
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

        public String getPaymentMethod() {
            return paymentMethod;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
