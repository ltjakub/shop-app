package pl.shop.shopapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import pl.shop.shopapp.product.ProductDto;

public class JsonConverter {
    public static String convertToJson(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ProductDto applyPatch(ProductDto productDto, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonProduct = objectMapper.valueToTree(productDto);
        JsonNode patchedJsonProduct = patch.apply(jsonProduct);
            return objectMapper.treeToValue(patchedJsonProduct, ProductDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
