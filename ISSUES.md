# M1

## List of issues

### Issue 1: ["Delete Profile" button does not work]

**Description**: [Upon clicking the delete button, user gets logged out of application, but they can sign in again, they do not need to sign up]

**How it was fixed?**: []

### Issue 2: [Log Out option is missing]

**Description**: [How do I log out of this app? Yes, I know that delete account loggs you off in the original app, but this does need re-sorting: there shall be a dedicated sign-out, and delete account should sign you out WITH account deletion]

**How it was fixed?**: [A sign out button was created, following what the non-repaired delete account button was doing, which was indeed signing out and not deleting the account. Required updating the strings xml, Navigation.kt, NavigationStateManager.kt, AuthViewModel.kt and ProfileScreen.kt]

### Issue 3: [Profile Picture disappears after clicking any "Save" button in the app]

**Description**: []

**How it was fixed?**: [WRITE_ISSUE_SOLUTION]

### Issue 4: [In Profile editor, the "Bio" text box is not selectable]

**Description**: [User cannot select the textbox and, as such cannot edit the Bio. It is expected that users are able to edit their bio after account creation, this is the case in Whatsapp and FB, for example.]

**How it was fixed?**: [The bio text box has been taken out of an ufocusable Row, and its readOnly has been changed from true to false.]

### Issue 5: [No biography request after account delete and re-creation]

**Description**: [After deleting the user and singing up with the same google account as of the deleted user the request to fill in the biography was not present, instead, the app jumped to the "Welcome" screen]

**How it was fixed?**: [In NavigationStateManager, when handling delete, needsProfileCompletion was set to false, but clearly it should be true, as we assume the bio was deleted together with the user, so we have to enter the bio again. Cross-checked that after deleting one account, another non-deleted account will not need to update bio, which was the risk associated with setting needsProfileCompletion to true]