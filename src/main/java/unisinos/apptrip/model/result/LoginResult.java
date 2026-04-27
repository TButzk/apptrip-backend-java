package unisinos.apptrip.model.result;

import unisinos.apptrip.model.User;

public record LoginResult(User user, String token) {
}

