-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项', '2000', '1', 'sysitem', 'ctag/sysitem/index', 1, 'C', '0', '0', 'ctag:sysitem:list', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '特征项菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项查询', @parentId, '1',  '#', '', 1,  'F', '0',  '0', 'ctag:sysitem:query',        '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项新增', @parentId, '2',  '#', '', 1,  'F', '0',  '0', 'ctag:sysitem:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项修改', @parentId, '3',  '#', '', 1,  'F', '0',  '0', 'ctag:sysitem:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项删除', @parentId, '4',  '#', '', 1,  'F', '0',  '0', 'ctag:sysitem:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项导出', @parentId, '5',  '#', '', 1,  'F', '0',  '0', 'ctag:sysitem:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
