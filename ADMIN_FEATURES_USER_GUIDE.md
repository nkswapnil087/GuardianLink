# Admin Features Quick Reference Guide

## System Admin Dashboard

### How to Add a New Organization

1. Log in as System Admin
2. Click **"MANAGE ORGANIZATIONS"** button in the sidebar
3. Click **"+ Add New Organization"** button
4. Fill in the form:
   - **Name**: Organization name (e.g., "Hope Foundation")
   - **Address**: Physical address (e.g., "123 Main St, Dhaka")
   - **Phone**: Contact number (e.g., "01712345678")
   - **Email**: Email address (e.g., "info@hope.org")
5. Click **"Create"**
6. Confirmation message will appear with the new organization ID

### How to Assign an Organization Admin

1. In **"MANAGE ORGANIZATIONS"** page
2. Find the organization you want to assign an admin to
3. Click **"Assign Admin"** button in the Actions column
4. Fill in the form:
   - **Username**: Create a unique username
   - **Password**: Set a strong password
   - **Full Name**: Name of the admin (e.g., "John Doe")
   - **Email**: Admin's email address
5. Click **"Assign"**
6. Success message will show the admin has been created

### How to Delete an Organization

1. In **"MANAGE ORGANIZATIONS"** page
2. Find the organization you want to delete
3. Click **"Delete"** button in the Actions column
4. Confirm the deletion when prompted
5. Organization will be removed from the system

### How to Manage All Children

1. Log in as System Admin
2. Click **"MANAGE CHILDREN"** button in the sidebar
3. You can see **ALL children** from **ALL organizations**
4. Actions available:
   - **Edit**: Update child's name, age, and sponsorship status
   - **Delete**: Remove a child from the system
   - **Add**: Create new children and assign to any organization

---

## Organization Admin Dashboard

### How to Manage Your Organization's Children

1. Log in as Organization Admin
2. Click **"MANAGE CHILDREN"** button in the sidebar
3. You will see **ONLY children from your organization**
4. Actions available:
   - **Edit**: Update child's name, age, and sponsorship status
   - **Delete**: Remove a child from the system
   - **Add**: Create new children (automatically assigned to your organization)

### What You Cannot Do (by Design)

❌ Cannot add organizations  
❌ Cannot delete organizations  
❌ Cannot assign other organization admins  
❌ Cannot see children from other organizations  
❌ Cannot access "Manage Organizations" section

---

## Navigation Guide

### System Admin Sidebar

```
Dashboard
    ├─ View system statistics
    └─ Total children, sponsored children, total donations

Manage Children
    ├─ View all children from all organizations
    ├─ Add new children
    ├─ Edit child information
    └─ Delete children

View Donations
    ├─ See all donations made
    └─ View donation details

Manage Organizations ⭐ (NEW)
    ├─ Add new organizations
    ├─ View all organizations
    ├─ Assign organization admins
    └─ Delete organizations

Logout
    └─ Sign out from the system
```

### Organization Admin Sidebar

```
Dashboard
    ├─ View organization statistics
    └─ Total children, sponsored children, donations

Manage Children
    ├─ View children in your organization only
    ├─ Add new children
    ├─ Edit child information
    └─ Delete children

View Donations
    ├─ See donations for your organization
    └─ View donation details

Logout
    └─ Sign out from the system
```

---

## Sample Test Data

### Organizations (Pre-populated)

- **ORG001**: Hope Foundation, 123 Main St, Dhaka, 01712345678, info@hope.org
- **ORG002**: Children's Care BD, 456 Park Ave, Chittagong, 01798765432, contact@childrencare.org
- **ORG003**: Future Leaders, 789 School Rd, Sylhet, 01611223344, support@futureleaders.org

### Sample Organization Admin Creation

When you assign an admin to "Hope Foundation":

- Username: `hope_admin`
- Password: `SecurePass123`
- Full Name: `Sarah Ahmed`
- Email: `sarah@hopefoundation.org`
- Organization: `ORG001`

After creation, Sarah can log in with these credentials and manage Hope Foundation's children.

---

## Error Messages & Solutions

### "Only System Admins can add organizations"

**Problem**: You're not logged in as System Admin  
**Solution**: Log out and log in with System Admin credentials

### "Validation Error: All fields are required!"

**Problem**: You left a field empty in the form  
**Solution**: Fill in all fields (Name, Address, Phone, Email)

### "This action cannot be undone" (Delete Confirmation)

**Problem**: You clicked Delete  
**Solution**: Click OK to confirm deletion, or Cancel to keep the record

### Org Admin sees no children

**Problem**: No children assigned to their organization yet  
**Solution**: System Admin needs to add children and assign them to that organization

---

## Tips & Best Practices

1. **Organization Naming**: Use descriptive names so admins can easily identify organizations
2. **Admin Assignment**: Assign only one primary admin per organization
3. **Password Strength**: Create strong passwords for organization admins
4. **Backup Admin**: Consider creating backup admin account for each organization
5. **Audit**: Keep track of which admin manages which organization
6. **Cascading Deletions**: Deleting organization may affect associated children (verify before delete)

---

## Keyboard Shortcuts

- **Tab**: Navigate between form fields
- **Enter**: Submit form (if focus is on a button)
- **Escape**: Close dialog/form

---

## Support

For issues or questions:

1. Check this guide first
2. Review error messages carefully
3. Verify you have the correct role/permissions
4. Ensure all required fields are filled
5. Try logging out and logging back in
