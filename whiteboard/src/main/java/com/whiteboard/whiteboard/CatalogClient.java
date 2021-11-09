
package com.whiteboard.whiteboard;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * CatalogClient
 * <p>
 * A Client from Image catalog
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "post-id",
    "post-userid",
    "post-body",
    "meta-data"
})
public class CatalogClient {

    /**
     * The unique identifier for a post.
     * 
     */
    @JsonProperty("post-id")
    @JsonPropertyDescription("The unique identifier for a post.")
    private String postId;
    /**
     * The unique Identifier of the author.
     * 
     */
    @JsonProperty("post-userid")
    @JsonPropertyDescription("The unique Identifier of the author.")
    private String postUserid;
    /**
     * Contains the text of the post.
     * 
     */
    @JsonProperty("post-body")
    @JsonPropertyDescription("Contains the text of the post.")
    private String postBody;
    /**
     * Metadata item describing resource.
     * 
     */
    @JsonProperty("meta-data")
    @JsonPropertyDescription("Metadata item describing resource.")
    private List<CatalogMetaDatum> metaData = new ArrayList<CatalogMetaDatum>();

    /**
     * The unique identifier for a post.
     * 
     */
    @JsonProperty("post-id")
    public String getPostId() {
        return postId;
    }

    /**
     * The unique identifier for a post.
     * 
     */
    @JsonProperty("post-id")
    public void setPostId(String postId) {
        this.postId = postId;
    }

    /**
     * The unique Identifier of the author.
     * 
     */
    @JsonProperty("post-userid")
    public String getPostUserid() {
        return postUserid;
    }

    /**
     * The unique Identifier of the author.
     * 
     */
    @JsonProperty("post-userid")
    public void setPostUserid(String postUserid) {
        this.postUserid = postUserid;
    }

    /**
     * Contains the text of the post.
     * 
     */
    @JsonProperty("post-body")
    public String getPostBody() {
        return postBody;
    }

    /**
     * Contains the text of the post.
     * 
     */
    @JsonProperty("post-body")
    public void setPostBody(String postBody) {
        this.postBody = postBody;
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
        sb.append(CatalogClient.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("postId");
        sb.append('=');
        sb.append(((this.postId == null)?"<null>":this.postId));
        sb.append(',');
        sb.append("postUserid");
        sb.append('=');
        sb.append(((this.postUserid == null)?"<null>":this.postUserid));
        sb.append(',');
        sb.append("postBody");
        sb.append('=');
        sb.append(((this.postBody == null)?"<null>":this.postBody));
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
        result = ((result* 31)+((this.postUserid == null)? 0 :this.postUserid.hashCode()));
        result = ((result* 31)+((this.metaData == null)? 0 :this.metaData.hashCode()));
        result = ((result* 31)+((this.postId == null)? 0 :this.postId.hashCode()));
        result = ((result* 31)+((this.postBody == null)? 0 :this.postBody.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CatalogClient) == false) {
            return false;
        }
        CatalogClient rhs = ((CatalogClient) other);
        return (((((this.postUserid == rhs.postUserid)||((this.postUserid!= null)&&this.postUserid.equals(rhs.postUserid)))&&((this.metaData == rhs.metaData)||((this.metaData!= null)&&this.metaData.equals(rhs.metaData))))&&((this.postId == rhs.postId)||((this.postId!= null)&&this.postId.equals(rhs.postId))))&&((this.postBody == rhs.postBody)||((this.postBody!= null)&&this.postBody.equals(rhs.postBody))));
    }

}
