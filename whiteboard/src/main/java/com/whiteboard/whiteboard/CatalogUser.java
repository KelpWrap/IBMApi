
package com.whiteboard.whiteboard;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * CatalogUser
 * <p>
 * A user from user catalog
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "user-id",
    "user-name",
    "user-alias",
    "user-hashedPassword",
    "user-type",
    "meta-data"
})
public class CatalogUser {

    /**
     * The unique identifier for a user.
     * 
     */
    @JsonProperty("user-id")
    @JsonPropertyDescription("The unique identifier for a user.")
    private String userId;
    /**
     * User name.
     * 
     */
    @JsonProperty("user-name")
    @JsonPropertyDescription("User name.")
    private String userName;
    /**
     * Users name on posts.
     * 
     */
    @JsonProperty("user-alias")
    @JsonPropertyDescription("Users name on posts.")
    private String userAlias;
    /**
     * Users hashed password.
     * 
     */
    @JsonProperty("user-hashedPassword")
    @JsonPropertyDescription("Users hashed password.")
    private String userHashedPassword;
    /**
     * Defines which commands are available to the user.
     * 
     */
    @JsonProperty("user-type")
    @JsonPropertyDescription("Defines which commands are available to the user.")
    private String userType;
    /**
     * Metadata item describing resource.
     * 
     */
    @JsonProperty("meta-data")
    @JsonPropertyDescription("Metadata item describing resource.")
    private List<CatalogMetaDatum> metaData = new ArrayList<CatalogMetaDatum>();

    /**
     * The unique identifier for a user.
     * 
     */
    @JsonProperty("user-id")
    public String getUserId() {
        return userId;
    }

    /**
     * The unique identifier for a user.
     * 
     */
    @JsonProperty("user-id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * User name.
     * 
     */
    @JsonProperty("user-name")
    public String getUserName() {
        return userName;
    }

    /**
     * User name.
     * 
     */
    @JsonProperty("user-name")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Users name on posts.
     * 
     */
    @JsonProperty("user-alias")
    public String getUserAlias() {
        return userAlias;
    }

    /**
     * Users name on posts.
     * 
     */
    @JsonProperty("user-alias")
    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    /**
     * Users hashed password.
     * 
     */
    @JsonProperty("user-hashedPassword")
    public String getUserHashedPassword() {
        return userHashedPassword;
    }

    /**
     * Users hashed password.
     * 
     */
    @JsonProperty("user-hashedPassword")
    public void setUserHashedPassword(String userHashedPassword) {
        this.userHashedPassword = userHashedPassword;
    }

    /**
     * Defines which commands are available to the user.
     * 
     */
    @JsonProperty("user-type")
    public String getUserType() {
        return userType;
    }

    /**
     * Defines which commands are available to the user.
     * 
     */
    @JsonProperty("user-type")
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Metadata item describing resource.
     * 
     */
    @JsonProperty("meta-data")
    public List<CatalogMetaDatum> getMetaData() {
        return metaData;
    }

    /**
     * Metadata item describing resource.
     * 
     */
    @JsonProperty("meta-data")
    public void setMetaData(List<CatalogMetaDatum> metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CatalogUser.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("userName");
        sb.append('=');
        sb.append(((this.userName == null)?"<null>":this.userName));
        sb.append(',');
        sb.append("userAlias");
        sb.append('=');
        sb.append(((this.userAlias == null)?"<null>":this.userAlias));
        sb.append(',');
        sb.append("userHashedPassword");
        sb.append('=');
        sb.append(((this.userHashedPassword == null)?"<null>":this.userHashedPassword));
        sb.append(',');
        sb.append("userType");
        sb.append('=');
        sb.append(((this.userType == null)?"<null>":this.userType));
        sb.append(',');
        sb.append("metaData");
        sb.append('=');
        sb.append(((this.metaData == null)?"<null>":this.metaData));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.userHashedPassword == null)? 0 :this.userHashedPassword.hashCode()));
        result = ((result* 31)+((this.metaData == null)? 0 :this.metaData.hashCode()));
        result = ((result* 31)+((this.userAlias == null)? 0 :this.userAlias.hashCode()));
        result = ((result* 31)+((this.userType == null)? 0 :this.userType.hashCode()));
        result = ((result* 31)+((this.userName == null)? 0 :this.userName.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CatalogUser) == false) {
            return false;
        }
        CatalogUser rhs = ((CatalogUser) other);
        return (((((((this.userHashedPassword == rhs.userHashedPassword)||((this.userHashedPassword!= null)&&this.userHashedPassword.equals(rhs.userHashedPassword)))&&((this.metaData == rhs.metaData)||((this.metaData!= null)&&this.metaData.equals(rhs.metaData))))&&((this.userAlias == rhs.userAlias)||((this.userAlias!= null)&&this.userAlias.equals(rhs.userAlias))))&&((this.userType == rhs.userType)||((this.userType!= null)&&this.userType.equals(rhs.userType))))&&((this.userName == rhs.userName)||((this.userName!= null)&&this.userName.equals(rhs.userName))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))));
    }

}
