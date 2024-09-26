package com.example.myapplication

class AccountEntity(
    private var _firstName: String,
    private var _lastName: String,
    private var _email: String,
    private var _phone: String,
    private var _username: String,
    private var _password: String
) {
    var firstName: String
        get() = _firstName
        set(value) {
            _firstName = value
        }

    var lastName: String
        get() = _lastName
        set(value) {
            _lastName = value
        }

    var email: String
        get() = _email
        set(value) {
            _email = value
        }

    var phone: String
        get() = _phone
        set(value) {
            _phone = value
        }

    var username: String
        get() = _username
        set(value) {
            _username = value
        }

    var password: String
        get() = _password
        set(value) {
            _password = value
        }
}
