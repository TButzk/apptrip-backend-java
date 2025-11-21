package unisinos.tripverse.model.result;

import unisinos.tripverse.model.User;

public record LoginResult(User user, String token) {
}
