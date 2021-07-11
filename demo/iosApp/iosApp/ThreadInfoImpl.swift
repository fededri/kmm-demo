//
//  ThreadInfoImpl.swift
//  iosApp
//
//  Created by Federico Torres on 10/07/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

public class ThreadInfoImpl : ThreadInfo {
    public func isMainThread() -> Bool {
        return Thread.isMainThread
    }
}
