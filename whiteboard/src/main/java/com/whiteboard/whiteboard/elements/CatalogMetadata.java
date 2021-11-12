
package com.whiteboard.whiteboard.elements;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * CatalogMetaDatum
 * <p>
 * The meta data object comprising the array.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "user_id",
    "post_id",
    "name",
    "value"
})
public class CatalogMetadata implements Element{

    /**
     * Meta data eleement property user_id
     * 
     */
    @JsonProperty("user_id")
    @JsonPropertyDescription("Meta data eleement property user_id")
    private Integer userId;
    /**
     * Meta data eleement property post_id
     * 
     */
    @JsonProperty("post_id")
    @JsonPropertyDescription("Meta data eleement property post_id")
    private Integer postId;
    /**
     * Meta data element property name.
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("Meta data element property name.")
    private String name;
    /**
     * Meta data element property value.
     * 
     */
    @JsonProperty("value")
    @JsonPropertyDescription("Meta data element property value.")
    private String value;

    /**
     * Meta data eleement property user_id
     * 
     */
    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    /**
     * Meta data eleement property user_id
     * 
     */
    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Meta data eleement property post_id
     * 
     */
    @JsonProperty("post_id")
    public Integer getPostId() {
        return postId;
    }

    /**
     * Meta data eleement property post_id
     * 
     */
    @JsonProperty("post_id")
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * Meta data element property name.
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Meta data element property name.
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Meta data element property value.
     * 
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * Meta data element property value.
     * 
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CatalogMetadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("postId");
        sb.append('=');
        sb.append(((this.postId == null)?"<null>":this.postId));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
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
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.postId == null)? 0 :this.postId.hashCode()));
        result = ((result* 31)+((this.userId == null)? 0 :this.userId.hashCode()));
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CatalogMetadata) == false) {
            return false;
        }
        CatalogMetadata rhs = ((CatalogMetadata) other);
        return (((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.postId == rhs.postId)||((this.postId!= null)&&this.postId.equals(rhs.postId))))&&((this.userId == rhs.userId)||((this.userId!= null)&&this.userId.equals(rhs.userId))))&&((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value))));
    }

}
