//
//  DIContainer.swift
//  iosApp
//
//  Created by Federico Torres on 22/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

protocol DIContainerProtocol {
    func register<Component>(type: Component.Type, component: Any)
    func resolve<Component>(type: Component.Type) -> Component?
    func isRegistered<Component>(type: Component.Type) -> Bool
}

final class DIContainer: DIContainerProtocol {
    private var _components: [String: Any] = [:]

    static let shared = DIContainer()

    private init() {}

    func register<Component>(type: Component.Type, component: Any) {
        if !isRegistered(type: type) {
            self._components["\(type)"] = component
        }
    }

    func resolve<Component>(type: Component.Type) -> Component? {
        return self._components["\(type)"] as? Component
    }

    func isRegistered<Component>(type: Component.Type) -> Bool {
        return self._components["\(type)"] != nil
    }
}
